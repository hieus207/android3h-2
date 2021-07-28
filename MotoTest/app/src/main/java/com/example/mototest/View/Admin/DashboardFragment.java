package com.example.mototest.View.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mototest.Model.Question;
import com.example.mototest.R;
import com.example.mototest.View.Test.LayoutTest;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {
    private LinearLayout ll_accountmanager;
    private LinearLayout ll_testmanager;
    private LinearLayout ll_questionmanager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ll_accountmanager = (LinearLayout) v.findViewById(R.id.ll_accountmanager);
        ll_testmanager = (LinearLayout) v.findViewById(R.id.ll_testmanager) ;
        ll_questionmanager =(LinearLayout) v.findViewById(R.id.ll_questionmanager);
        ll_accountmanager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_nav_dashboard_to_accountmanager);

            }
        });
        ll_testmanager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_nav_dashboard_to_testmanager);

            }
        });
        ll_questionmanager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_nav_dashboard_to_questionmanager);
            }
        });



        return  v;
    }
}