package com.example.mototest.View.Comment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mototest.Api.AllCmt;
import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Api.Status;
import com.example.mototest.MainActivity;
import com.example.mototest.Model.Comment;
import com.example.mototest.Model.User;
import com.example.mototest.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class comment extends AppCompatActivity {
    private ImageView toolbar_back,iv_send;
    private EditText edt_cmt_content;
    private ArrayList<Comment> arrayList = new ArrayList<>();
    private CmtAdapter cmtAdapter;
    private ListView lv_comment;
    Activity activity=this;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        dialog=new Dialog(activity);
        dialog.setContentView(R.layout.loading);
        dialog.show();
        toolbar_back=(ImageView) findViewById(R.id.iv_toolbar_back);

        toolbar_back.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int Testid = bundle.getInt("TestId");
        getComment(Testid);
        edt_cmt_content = findViewById(R.id.edt_cmt_content);
        iv_send = findViewById(R.id.iv_send);
        lv_comment = findViewById(R.id.lv_comment);
        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String content = edt_cmt_content.getText().toString();
                if(content!=null)
                sendCmt(content,Integer.toString(Testid));
                edt_cmt_content.setText("");
                getComment(Testid);
            }
        });
//        Toast.makeText(this,Integer.toString(Testid),Toast.LENGTH_SHORT).show();
    }
    private void getComment(Integer Testid){
        ApiService.apiservice.getAllCmt("getAllCmt",Integer.toString(Testid)).enqueue(new Callback<AllCmt>() {
            @Override
            public void onResponse(Call<AllCmt> call, Response<AllCmt> response) {
                AllCmt allCmt = response.body();
                dialog.dismiss();
                arrayList = allCmt.getAllCmt();
                ArrayList<User> userArrayList= new ArrayList<>();
                userArrayList = allCmt.getInfoCmt();
//                Log.e("USER",userArrayList.get(0).getUsername());
//                Log.e("cmt:",arrayList.get(0).getContent());
                cmtAdapter = new CmtAdapter(getBaseContext(),arrayList,userArrayList);
                lv_comment.setAdapter(cmtAdapter);
            }

            @Override
            public void onFailure(Call<AllCmt> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getBaseContext(),"GET ALL CMT FAIL",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendCmt(String content,String Testid){

        ApiService.apiservice.createCmt("createCmt",Integer.toString(((InfoAcc) getApplication()).getIduser()),Testid,content,((InfoAcc) getApplication()).getAccess_token()).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                dialog.dismiss();
                Toast.makeText(getBaseContext(),"Đã đăng bình luận",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getBaseContext(),"Bình luận thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}