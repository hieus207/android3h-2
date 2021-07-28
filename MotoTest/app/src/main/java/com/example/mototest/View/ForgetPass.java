package com.example.mototest.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.Status;
import com.example.mototest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPass extends AppCompatActivity {
    Dialog dialog;
    Activity activity=this;
    Button btn_comfirm;
    EditText edt_forgot_confpass,edt_forgot_pass,edt_forgot_recover,edt_forgot_usn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        edt_forgot_usn = findViewById(R.id.edt_forgot_usn);
        edt_forgot_confpass = findViewById(R.id.edt_forgot_confpass);
        edt_forgot_pass = findViewById(R.id.edt_forgot_pass);
        edt_forgot_recover = findViewById(R.id.edt_forgot_recover);
        btn_comfirm=(Button)findViewById(R.id.btn_comfirm);
        btn_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(activity);
                dialog.setContentView(R.layout.loading);
                dialog.show();

                if(edt_forgot_pass.getText().toString().equals(edt_forgot_confpass.getText().toString())){
                    changePass();

                }
                else{
                    dialog.dismiss();
                    Toast.makeText(getBaseContext(),"Pass mới không trùng nhau",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
    private void changePass(){
        ApiService.apiservice.querryUser("RecoverPass",
                "",
                edt_forgot_usn.getText().toString(),
                edt_forgot_pass.getText().toString(),
                "",
                "",
                "",
                edt_forgot_recover.getText().toString(),
                "Khoi phuc k can token").enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                dialog.dismiss();
                Toast.makeText(getBaseContext(),"Khôi phục mật khẩu thành công",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ForgetPass.this,Login.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getBaseContext(),"Sai mã khôi phục hoặc tên tài khoản",Toast.LENGTH_SHORT).show();
            }
        });
    }
}