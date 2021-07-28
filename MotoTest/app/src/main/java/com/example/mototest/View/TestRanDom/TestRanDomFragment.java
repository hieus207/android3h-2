package com.example.mototest.View.TestRanDom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mototest.R;
import com.example.mototest.View.Test.LayoutTest;


public class TestRanDomFragment extends Fragment {
    private Button btn_TestRandom;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_testrandom, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_TestRandom=getActivity().findViewById(R.id.btn_TestRandom);
        btn_TestRandom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("Idtest", "random");
                Intent intent=new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), LayoutTest.class);
                getActivity().startActivity(intent);
            }
        });
    }
}