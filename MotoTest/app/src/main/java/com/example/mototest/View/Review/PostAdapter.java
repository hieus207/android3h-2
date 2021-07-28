package com.example.mototest.View.Review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mototest.Model.ContentPost;
import com.example.mototest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<ContentPost> {
    Context context;
    ArrayList<ContentPost> contentPostArrayList;
    public PostAdapter(@NonNull Context context, ArrayList<ContentPost> contentPostArrayList) {
        super(context,0,contentPostArrayList);
        this.context=context;
        this.contentPostArrayList=contentPostArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView=inflater.inflate(R.layout.items_post,parent,false);
        TextView content=(TextView)convertView.findViewById(R.id.tv_content_rv);
        ImageView img=(ImageView)convertView.findViewById(R.id.img_rv);
        ContentPost contentPost=contentPostArrayList.get(position);
        String url=contentPost.getImage();
        content.setText(contentPost.getContent());
        Picasso.get().load(url).into(img);
        return convertView;
    }
}
