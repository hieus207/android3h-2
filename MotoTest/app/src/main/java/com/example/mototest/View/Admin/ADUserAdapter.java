package com.example.mototest.View.Admin;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mototest.Model.User;
import com.example.mototest.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ADUserAdapter extends ArrayAdapter<User> {
    Context context;
    ArrayList<User> arrayList;
    int layoutResource; //Hàm khởi tạo cho CustomArrayAdapter

    public ADUserAdapter(@NonNull @NotNull Context context, int resource, ArrayList<User> arrayList) {
        super(context, resource, arrayList);
        this.arrayList = arrayList;
        this.context = context;
        this.layoutResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            Log.e("Position =",Integer.toString(position));
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView=inflater.inflate(R.layout.fragment_rowaccount,parent,false);
        TextView tv_username = (TextView) convertView.findViewById(R.id.tv_username);
        TextView tv_recover = (TextView) convertView.findViewById(R.id.tv_Recover);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_permission = (TextView) convertView.findViewById(R.id.tv_permission);
        User user = arrayList.get(position);
        tv_username.setText("Username: "+user.getUsername());
        tv_name.setText("Tên: "+user.getName());
        tv_permission.setText(user.getPermission());
        tv_recover.setText("Mã khôi phục: "+user.getRecover());
        if(user.getActive()!=1) convertView.setBackgroundColor(Color.parseColor("#F65B65"));
        return convertView;
    }
}
