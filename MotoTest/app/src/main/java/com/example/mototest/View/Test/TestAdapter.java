package com.example.mototest.View.Test;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Api.Status;
import com.example.mototest.MainActivity;
import com.example.mototest.R;
import com.example.mototest.View.Admin.testmanager;
import com.example.mototest.View.Comment.comment;

import java.util.ArrayList;
import java.util.EventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestAdapter extends ArrayAdapter<String> {
    public static boolean isdel=false;
    private Context context;
    private String access_token;
    private ArrayList<String> testArrayList=new ArrayList<>();
    EventListener listener;
    public TestAdapter(@NonNull Context context, ArrayList<String> testArrayList,EventListener listener) {
        super(context, 0,testArrayList);
        this.testArrayList=testArrayList;
        this.context=context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.items_test,parent,false);
        }
        TextView test_name=(TextView)convertView.findViewById(R.id.tv_test_name);
        ImageView iv_show_cmt = convertView.findViewById(R.id.iv_show_cmt);

        access_token = ((InfoAcc) getContext().getApplicationContext()).getAccess_token();
        iv_show_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("TestId",Integer.parseInt(testArrayList.get(position)));
                Intent intent = new Intent(getContext(), comment.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


        ImageView img_delTest = convertView.findViewById(R.id.img_delTest);
        if(parent.getId()==R.id.lv_testmanager)
            img_delTest.setVisibility(View.VISIBLE);
            img_delTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isdel=true;

                confirmDel(testArrayList.get(position));
//                Toast.makeText(getContext(), "Hay f5 -> da xoa test:"+testArrayList.get(position), Toast.LENGTH_SHORT).show();
//                isdel=false;
            }
        });


        String test=getItem(position);
        if(test!=null){
            test_name.setText("Đề "+test);
        }

        return convertView;
    }

    public interface EventListener {
        void onEvent(int data);
    }

    private void delTest(String testId){
        ApiService.apiservice.querryTest("delTest",testId,"0",access_token).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                listener.onEvent(1);
                Toast.makeText(getContext(),"Xóa bài thi thành công",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getContext(),"Xóa thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirmDel(String testid){

        Dialog dialog=new Dialog(context);
//        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
        dialog.setContentView(R.layout.dialog_custom);

        Button btn_yes=(Button)dialog.findViewById(R.id.btn_yes);
        Button btn_no=(Button)dialog.findViewById(R.id.btn_no);
        TextView tv_dialog_title= dialog.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_content=dialog.findViewById(R.id.tv_dialog_content);
        tv_dialog_title.setText("Xác nhận Xóa");
        tv_dialog_content.setText("Bạn có chắc muốn xóa bài thi này chứ");
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                delTest(testid);

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
