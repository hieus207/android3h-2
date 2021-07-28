package com.example.mototest.View.Test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.mototest.Api.Alltest;
import com.example.mototest.Api.ApiService;
import com.example.mototest.Model.DBHandler;
import com.example.mototest.Model.Test;
import com.example.mototest.R;
import com.example.mototest.View.Login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TestFragment extends Fragment implements TestAdapter.EventListener {
    Button button;
    TestAdapter testAdapter;
    ListView listViewtest;
    ArrayList<String> testArrayList = new ArrayList<String>();
    Dialog dialog2;
    private DBHandler dbHandler ;
    public TestFragment() {
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                confirmLogout();
                // Handle the back button event
//                Toast.makeText(getContext(),"TEST",Toast.LENGTH_SHORT).show();
            }
        };
        dbHandler = new DBHandler(this.getContext());
        getActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_test,container,false);
        dialog2=new Dialog(getActivity());
        dialog2.setContentView(R.layout.loading);
        dialog2.show();

        // Inflate the layout for this fragment
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewtest=(ListView)getActivity().findViewById(R.id.lv_test);
        if(true){
            dialog2.dismiss();
            listViewtest=(ListView)getActivity().findViewById(R.id.lv_test);
            ArrayList<Test> allidTest=dbHandler.getAllTest();
            for(Test t : allidTest)
            {
                testArrayList.add(Integer.toString(t.getIdtest()));
            }
            testAdapter=new TestAdapter(getActivity(),testArrayList,TestFragment.this::onEvent);
            listViewtest.setAdapter(testAdapter);
            listViewtest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle=new Bundle();
                    bundle.putString("Idtest", testArrayList.get(position));
                    Intent intent=new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), LayoutTest.class);
                    getActivity().startActivity(intent);
                }
            });
        }
        else
        ApiService.apiservice.getAllTest("getAllTest").enqueue(new Callback<Alltest>() {
            @Override
            public void onResponse(Call<Alltest> call, Response<Alltest> response) {
                dialog2.dismiss();

//                Toast.makeText(getContext(), "Call API SUCCESS", Toast.LENGTH_SHORT).show();
                Alltest alltest=response.body();
                ArrayList<Test> allidTest=alltest.getAllTest();
//                Log.e("testid 1:",Integer.toString(alltest.getAllTest().get(0).getIdtest()));
                for(Test t : allidTest)
                {
                    testArrayList.add(Integer.toString(t.getIdtest()));
                }
                testAdapter=new TestAdapter(getActivity(),testArrayList,TestFragment.this::onEvent);
                listViewtest.setAdapter(testAdapter);
                listViewtest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle bundle=new Bundle();
                        bundle.putString("Idtest", testArrayList.get(position));
                        Intent intent=new Intent();
                        intent.putExtras(bundle);
                        intent.setClass(getActivity(), LayoutTest.class);
                        getActivity().startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<Alltest> call, Throwable t) {
                dialog2.dismiss();
                Toast.makeText(getContext(), "Lấy dữ liệu bài thi thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void confirmLogout(){
        Dialog dialog=new Dialog(getActivity());
//        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
        dialog.setContentView(R.layout.dialog_custom);

        Button btn_yes=(Button)dialog.findViewById(R.id.btn_yes);
        Button btn_no=(Button)dialog.findViewById(R.id.btn_no);
        TextView tv_dialog_title= dialog.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_content=dialog.findViewById(R.id.tv_dialog_content);
        tv_dialog_title.setText("Xác nhận đăng xuất");
        tv_dialog_content.setText("Bạn có chắc muốn đăng xuất chứ");
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getActivity().finish();
                Intent intent=new Intent(getContext(), Login.class);
                startActivity(intent);
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

    @Override
    public void onEvent(int data) {

    }



}