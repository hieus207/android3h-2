package com.example.mototest.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.Api.AllQues;
import com.example.mototest.Api.AllTestQS;
import com.example.mototest.Api.Alltest;
import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Api.TestQS;
import com.example.mototest.MainActivity;
import com.example.mototest.Model.DBHandler;
import com.example.mototest.Model.Question;
import com.example.mototest.Model.Test;
import com.example.mototest.Model.User;
import com.example.mototest.R;
import com.example.mototest.View.Admin.testmanager;
import com.example.mototest.View.Admin.testmanagerDirections;
import com.example.mototest.View.Test.TestAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button btn_login,btn_res;
    TextView tv_res,tv_forgetpass;
    EditText edt_usn_login,edt_pass_login;
    private int i=0;
    Activity activity=this;
    Dialog dialog2;
//    Test test = new Test(4,null,null);
//    Question question = new Question(3,"d","f","d","a","a","a","a","1");
    private DBHandler dbHandler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHandler = new DBHandler(this);
        btn_login = (Button)findViewById(R.id.btn_login);
        tv_res = (TextView) findViewById(R.id.tv_register);
        tv_forgetpass=(TextView) findViewById(R.id.tv_forgetpass);
        edt_usn_login=(EditText)findViewById(R.id.edt_usn_login);
        edt_pass_login=(EditText)findViewById(R.id.edt_pass_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog2=new Dialog(activity);
                dialog2.setContentView(R.layout.loading);
                dialog2.show();
                insertTest();
                insertQues();
                login();
//                dbHandler.getAllTest();
//                dbHandler.getAllQuestion();
////               dbHandler.addTest(test);
////                dbHandler.updateTest(test);
//                dbHandler.getTest(4);
////               dbHandler.deleteTest(test);
////               dbHandler.addQuestion(question);
////               dbHandler.deleteQuestion(question);
////               dbHandler.updateQuestion(question);
////               dbHandler.getTest(4);
//                dbHandler.getQuestion(1);
            }
        });
        tv_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        tv_forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ForgetPass.class);
                startActivity(intent);
            }
        });
    }

    private void login(){
        ApiService.apiservice.login(edt_usn_login.getText().toString(),edt_pass_login.getText().toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user =response.body();
                ((InfoAcc) getApplication()).setUsername(user.getUsername());
                ((InfoAcc) getApplication()).setName(user.getName());
                ((InfoAcc) getApplication()).setIduser(user.getIduser());
                ((InfoAcc) getApplication()).setPermission(user.getPermission());
                ((InfoAcc) getApplication()).setAccess_token(user.getAccess_token());
                Intent intent=new Intent(Login.this, MainActivity.class);
                dialog2.dismiss();
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                dialog2.dismiss();
            }
        });
    }

    private void insertQues(){
//        INSERT ALL QS
        ApiService.apiservice.getAllQues("getAllQS").enqueue(new Callback<AllQues>() {
            @Override
            public void onResponse(Call<AllQues> call, Response<AllQues> response) {
                dialog2.dismiss();
//                    Toast.makeText(getContext(),"CALL API SUCCESS",Toast.LENGTH_SHORT).show();
                AllQues allQues = response.body();
                ArrayList<Question> arrayList = allQues.getAllQues();
                for(Question q:arrayList)
                    dbHandler.addQuestion(q);

//                    Log.e("size",Integer.toString(arrayList.size()));
            }

            @Override
            public void onFailure(Call<AllQues> call, Throwable t) {
                dialog2.dismiss();
                Toast.makeText(getBaseContext(),"Lấy danh sách câu hỏi thất bại",Toast.LENGTH_SHORT).show();
            }
        });
////        INSERT ALL TEST

    }
    private void insertTest(){
        ApiService.apiservice.getAllTestAndQS("getAllTestAndQS").enqueue(new Callback<AllTestQS>() {
            @Override
            public void onResponse(Call<AllTestQS> call, Response<AllTestQS> response) {
//                lv_test=(ListView)getActivity().findViewById(R.id.lv_test);
//                Toast.makeText(getContext(), "Call API SUCCESS", Toast.LENGTH_SHORT).show();
                AllTestQS alltest=response.body();
//                Toast.makeText(getBaseContext(), "lấy dữ liệu bài thi thành công 222", Toast.LENGTH_SHORT).show();
                ArrayList<TestQS> listTest = alltest.getAllTest();
//                testArrayList = new ArrayList<String>();
                int i=0;
                for(TestQS t : listTest)
                {
                    i++;
                    dbHandler.addTest(t);
                }
                Log.e("Tong so",Integer.toString(i));

//                Toast.makeText(getContext(), "Call API get test 1 lan", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<AllTestQS> call, Throwable t) {
//                dialog2.dismiss();
                Toast.makeText(getBaseContext(), "lấy dữ liệu bài thi thất bại 222", Toast.LENGTH_SHORT).show();
            }
        });
    }
}