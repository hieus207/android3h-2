package com.example.mototest.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.Api.ApiService;
import com.example.mototest.Model.User;
import com.example.mototest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText edt_res_username,edt_res_name,edt_res_password,edt_res_ComPassword;
    private Button btn_res_register;
    TextView tv_login;
    Activity activity=this;
    Dialog dialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_res_register=findViewById(R.id.btn_res_register);
        edt_res_username=findViewById(R.id.edt_res_username);
        edt_res_name=findViewById(R.id.edt_res_name);
        edt_res_password=findViewById(R.id.edt_res_password);
        edt_res_ComPassword=findViewById(R.id.edt_res_ComPassword);
        tv_login = (TextView)findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        btn_res_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_res_password.getText().toString().equals(edt_res_ComPassword.getText().toString())){
                    dialog2=new Dialog(activity);
                    dialog2.setContentView(R.layout.loading);
                    dialog2.show();
                    register();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Pass khong trung nhau",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void register(){
        ApiService.apiservice.register(edt_res_name.getText().toString(),edt_res_username.getText().toString(),edt_res_password.getText().toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user =response.body();
                if(user.getUsername()!=null){
                    Dialog dialog=new Dialog(activity);
                    dialog.setContentView(R.layout.dialog_custom);
                    Button btn_yes=(Button)dialog.findViewById(R.id.btn_yes);
                    Button btn_no=(Button)dialog.findViewById(R.id.btn_no);
                    btn_no.setVisibility(View.GONE);
                    btn_yes.setText("Đồng Ý, đăng nhập");
                    TextView tv_dialog_title= dialog.findViewById(R.id.tv_dialog_title);
                    TextView tv_dialog_content=dialog.findViewById(R.id.tv_dialog_content);
                    tv_dialog_title.setText("Tạo tài khoản thành công");
                    tv_dialog_content.setText("Chào "+user.getUsername()+", bạn hãy lưu mã khôi phục pass: "+user.getRecover());
                    dialog2.dismiss();
                    btn_yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intent=new Intent(Register.this,Login.class);
                            startActivity(intent);
                        }
                    });
                    dialog.show();
//                    Toast.makeText(getBaseContext(),"REG THANH CONG",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getBaseContext(),"Ma RECOVER PASS:"+user.getRecover(),Toast.LENGTH_SHORT).show();
                }

//                CHUYEN SANG VIEW LOGIN
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog2.dismiss();
                Toast.makeText(getBaseContext(),"Tên tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}