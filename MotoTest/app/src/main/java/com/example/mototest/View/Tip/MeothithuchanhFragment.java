package com.example.mototest.View.Tip;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mototest.Api.ApiService;
import com.example.mototest.Model.ContentPost;
import com.example.mototest.Model.Post;
import com.example.mototest.R;
import com.example.mototest.View.Review.PostAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeothithuchanhFragment extends Fragment {
    ArrayList<ContentPost> contentPostArrayList=new ArrayList<>();
    Context context;
    ListView listView;
    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_meothithuchanh, container, false);
        listView=(ListView)v.findViewById(R.id.lv_mtth);
        context=container.getContext();
        dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.loading);
        dialog.show();
        callapi();
        return v;
    }

    private void callapi() {
        ApiService.apiservice2.getMeothith("getMTThuchanh").enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post=response.body();
                contentPostArrayList=post.getMTTH();
                PostAdapter postAdapter=new PostAdapter(context,contentPostArrayList);
                dialog.dismiss();
                listView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("loi","loi");
                dialog.dismiss();
            }
        });
    }
}