package com.example.mototest.View.Admin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Api.Status;
import com.example.mototest.Model.User;
import com.example.mototest.R;
import com.example.mototest.View.Login;
import com.example.mototest.View.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link infoaccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoaccount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText edt_username;
    private EditText edt_recoverCode;
    private EditText edt_name;
    private RadioGroup rbtn_permission;
    private RadioButton rbtn_admin;
    private RadioButton rbtn_user;
    private Button btn_update,btn_delete;
    private Button btn_active;
    private TextView tv_iduser;
    private String access_token;
    Dialog dialog2;
    Activity activity=getActivity();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private User user=new User(1,"abc","123","huy","2",1,"0");

    public infoaccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoaccount.
     */
    // TODO: Rename and change types and number of parameters
    public static infoaccount newInstance(String param1, String param2) {
        infoaccount fragment = new infoaccount();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_infoaccount, container, false);
        dialog2=new Dialog(getActivity());
        dialog2.setContentView(R.layout.loading);
        try{
//code that may throw an exception
            user = infoaccountArgs.fromBundle(getArguments()).getUser();
        }catch(Exception e){

        }
        btn_delete = (Button) v.findViewById(R.id.btn_delete);
        edt_username = (EditText) v.findViewById(R.id.edt_username);
        edt_recoverCode = (EditText) v.findViewById(R.id.edt_recoverCode);
        edt_name = (EditText) v.findViewById(R.id.edt_name);
        rbtn_permission = (RadioGroup) v.findViewById(R.id.rbtn_permission);
        rbtn_admin = (RadioButton) v.findViewById(R.id.rbtn_admin);
        rbtn_user = (RadioButton) v.findViewById(R.id.rbtn_user);
        btn_update = (Button) v.findViewById(R.id.btn_update);
        btn_active = (Button) v.findViewById(R.id.btn_active);
        access_token =((InfoAcc) getActivity().getApplication()).getAccess_token();
//        if(user.getActive()!=1){
//            btn_active.setText("Unblock");
//        }
        btn_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockUser(user.getActive());
            }
        });
        tv_iduser = (TextView) v.findViewById(R.id.tv_iduser);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.show();
                updateUser(user.getActive());
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.show();
//                Toast.makeText(getContext(),"Chay delete",Toast.LENGTH_SHORT).show();
                confirmDel();

            }
        });
        tv_iduser.setText("ID : "+Integer.toString(user.getIduser()));
        edt_username.setText(user.getUsername().toString());
        edt_name.setText(user.getName());
        edt_recoverCode.setText(user.getRecover());
        if(user.getActive()!=1)
            btn_active.setText("UNBLOCK");
        if(user.getPermission().equals("admin"))
            rbtn_admin.setChecked(true);
        else
            rbtn_user.setChecked(true);


        return v;
    }

    private void updateUser(int active){
        String permission=rbtn_admin.isChecked()?"admin":"user";
        ApiService.apiservice.querryUser("updateUser",
                Integer.toString(user.getIduser()),
                edt_username.getText().toString(),
                "",
                edt_name.getText().toString(),
                permission,
                Integer.toString(active),
                edt_recoverCode.getText().toString(),
                access_token)
                .enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        dialog2.dismiss();
                        if(active==-1) {
                            btn_active.setText("UNBLOCK");
                            Toast.makeText(getContext(),"Block thành công",Toast.LENGTH_SHORT).show();
                            user.setActive(-1);
                        }else
                            if(active==1) btn_active.setText("BLOCK");
                            Toast.makeText(getContext(),"Update thành công",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        dialog2.dismiss();
                        Toast.makeText(getContext(),"Thao tác thất bại",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void blockUser(int active){
        int blockid;
        if(active==1){
            blockid=-1;
        }
        else
            blockid=1;
        updateUser(blockid);
    }

    private void deleteUser(){
        ApiService.apiservice.querryUser(
                "deleteUser",
                Integer.toString(user.getIduser()),
                "g",
                "g",
                "g",
                "g",
                "g",
                "g",
                access_token
        ).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                dialog2.dismiss();
//                Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"Đã xóa người dùng thành công",Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                dialog2.dismiss();
                Toast.makeText(getContext(),"Xóa người dùng thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirmDel(){
        dialog2.dismiss();
        Dialog dialog=new Dialog(getActivity());
//        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
        dialog.setContentView(R.layout.dialog_custom);

        Button btn_yes=(Button)dialog.findViewById(R.id.btn_yes);
        Button btn_no=(Button)dialog.findViewById(R.id.btn_no);
        TextView tv_dialog_title= dialog.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_content=dialog.findViewById(R.id.tv_dialog_content);
        tv_dialog_title.setText("Xác nhận Xóa");
        tv_dialog_content.setText("Bạn có chắc muốn xóa người dùng này chứ");
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                deleteUser();

            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }
}