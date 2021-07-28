package com.example.mototest.View.Comment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mototest.Model.Comment;
import com.example.mototest.Model.User;
import com.example.mototest.R;

import java.util.ArrayList;
import java.util.Arrays;

public class CmtAdapter extends ArrayAdapter<Comment> {
    private Context context;
    private ArrayList<Comment> cmtArrayList=new ArrayList<>();
    private TextView tv_cmt_guess,tv_cmt_usn,tv_cmt_name;
    private ArrayList<User> userArrayList;
    public CmtAdapter(@NonNull Context context, ArrayList<Comment> cmtArrayList, ArrayList<User> userArrayList) {
        super(context, 0,cmtArrayList);
        this.cmtArrayList=cmtArrayList;
        this.userArrayList = userArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.items_cmt,parent,false);
        }
        tv_cmt_name = convertView.findViewById(R.id.tv_cmt_name);
        tv_cmt_usn = convertView.findViewById(R.id.tv_cmt_usn);
        tv_cmt_guess = convertView.findViewById(R.id.tv_cmt_guess);
        tv_cmt_guess.setText(cmtArrayList.get(position).getContent());
        String name =  userArrayList.get(findEle(cmtArrayList.get(position).getIduser())).getName();
        String username =  userArrayList.get(findEle(cmtArrayList.get(position).getIduser())).getUsername();
        tv_cmt_name.setText(name);
        tv_cmt_usn.setText(username);

        return convertView;
    }

    private int findEle(int Id){
        int i=0;
        for(User user: userArrayList){
            if(user.getIduser()==Id)
                return i;
            i++;
        }
        return -1;
    }
}
