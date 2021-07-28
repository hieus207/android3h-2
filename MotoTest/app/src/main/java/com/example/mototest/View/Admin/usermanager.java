package com.example.mototest.View.Admin;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mototest.Api.AllUser;
import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Model.User;
import com.example.mototest.R;
import com.example.mototest.View.Test.TestAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link usermanager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class usermanager extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ADUserAdapter adUserAdapter;
    private ArrayList<User> UserArrayList = new ArrayList<>();
    private ListView lv_account;
    Dialog dialog2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String access_token;
    private String Iduser;


    public usermanager() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment accountmanager.
     */
    // TODO: Rename and change types and number of parameters
    public static usermanager newInstance(String param1, String param2) {
        usermanager fragment = new usermanager();
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
        View v = inflater.inflate(R.layout.fragment_usermanager, container, false);
        dialog2=new Dialog(getActivity());
        dialog2.setContentView(R.layout.loading);
        dialog2.show();
//        UserArrayList.add(new User(1,"abc","123","huy","2",1,"0"));
        access_token =((InfoAcc) getActivity().getApplication()).getAccess_token();
        Iduser =Integer.toString(((InfoAcc) getActivity().getApplication()).getIduser());
        lv_account = v.findViewById(R.id.lv_account);
        getAllUser();
        return v;
    }

    public void getAllUser(){
        ApiService.apiservice.getAllUser("getAllUser",Iduser,access_token).enqueue(new Callback<AllUser>() {
            @Override
            public void onResponse(Call<AllUser> call, Response<AllUser> response) {
                AllUser allUser = response.body();
                UserArrayList = allUser.getAllUser();
                dialog2.dismiss();
                adUserAdapter = new ADUserAdapter(getContext(),R.layout.fragment_rowaccount,UserArrayList);
                lv_account.setAdapter(adUserAdapter);
                lv_account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NavDirections action = usermanagerDirections.actionAccountmanagerToInfoaccount(UserArrayList.get(position));
                        NavController navController = Navigation.findNavController(getView());
                        navController.navigate(action);
                    }
                });
            }

            @Override
            public void onFailure(Call<AllUser> call, Throwable t) {
                dialog2.dismiss();
                Toast.makeText(getContext(),"Không lấy được danh sách user",Toast.LENGTH_SHORT).show();
                Log.e("access_token",access_token+"loi: "+t.toString());
            }
        });

    }
}