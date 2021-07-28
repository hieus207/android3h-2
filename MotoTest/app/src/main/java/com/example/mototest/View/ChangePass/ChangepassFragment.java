package com.example.mototest.View.ChangePass;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Api.Status;
import com.example.mototest.R;
import com.example.mototest.View.Login;
import com.example.mototest.View.Test.LayoutTest;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangepassFragment extends Fragment {

    private EditText edt_changepass_current,edt_changepass_new,edt_changepass_confnew;
    private Button btn_save;
    private String access_token,username;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_changepass, container, false);
        edt_changepass_current = v.findViewById(R.id.edt_changepass_current);
        edt_changepass_new = v.findViewById(R.id.edt_changepass_new);
        edt_changepass_confnew = v.findViewById(R.id.edt_changepass_confnew);
        access_token =((InfoAcc) getActivity().getApplication()).getAccess_token();
        username =((InfoAcc) getActivity().getApplication()).getUsername();
        btn_save=v.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.loading);
                dialog.show();
                if(edt_changepass_new.getText().toString().equals(edt_changepass_confnew.getText().toString()))
                changePass();
                else
                    Toast.makeText(getContext(),"Mật khẩu mới không trùng nhau",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    private void changePass(){
        ApiService.apiservice.querryUser(
                "ChangePass",
                Integer.toString(((InfoAcc) getActivity().getApplication()).getIduser()),
                username,
                edt_changepass_new.getText().toString(),
//                TẬN DỤNG FIELD NAME ĐỂ CHỨA PASS CŨ ĐỠ VIẾT API MỚI
                edt_changepass_current.getText().toString(),
                "",
                "",
                "",
                access_token
        ).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                dialog.dismiss();
                Toast.makeText(getContext(),response.body().getStatus(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(),"Change Pass Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

}