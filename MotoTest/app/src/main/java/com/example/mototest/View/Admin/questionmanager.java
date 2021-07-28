package com.example.mototest.View.Admin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mototest.Api.AllQues;
import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Api.Status;
import com.example.mototest.MainActivity;
import com.example.mototest.Model.DBHandler;
import com.example.mototest.Model.Question;
import com.example.mototest.Model.Test;
import com.example.mototest.R;
import com.example.mototest.View.Login;
import com.example.mototest.View.Test.LayoutTest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class questionmanager extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btn_newquestion;
    private ArrayList<Question> arrayList = new ArrayList<Question>();
    private ArrayList<String> addqsList = new ArrayList<String>();
    private ArrayList<String> rmqsList = new ArrayList<String>();
    private ADQuestionAdapter adQuestionAdapter;
    private ListView lv_question;
    private Integer TestId=0;
    private CheckBox cb_addtoTest;
    private Button btn_addtoTest,btn_remove;
    private LinearLayout ll_rowques;
    private int current_index=0;
    private int type=0;
    Dialog dialog2;
    private String access_token;
    private SearchView searchView;
    private EditText edts;
    private DBHandler dbHandler ;

    public questionmanager() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        try {
            TestId=Integer.parseInt(questionmanagerArgs.fromBundle(getArguments()).getTestID());
        }
        catch (Exception e){
            TestId=0;
            Log.e("abc","Dinh exception");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_questionmanager, container, false);
        setAllQS(TestId);
        dialog2=new Dialog(getActivity());
        dialog2.setContentView(R.layout.loading);
        dialog2.show();
        btn_newquestion = (Button) v.findViewById(R.id.btn_newquestion);
        searchView=(SearchView) v.findViewById(R.id.search_bar);
        btn_newquestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_questionmanager_to_infoquestion);

            }
        });
        access_token = ((InfoAcc) getActivity().getApplication()).getAccess_token();
        btn_remove=(Button) v.findViewById(R.id.btn_remove);
        btn_addtoTest = (Button) v.findViewById(R.id.btn_addtoTest);
        if(TestId!=0){
            btn_addtoTest.setVisibility(View.VISIBLE);
            btn_remove.setVisibility(View.VISIBLE);
            btn_newquestion.setVisibility(View.GONE);
        }

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.show();
                removeQSinTest();
            }
        });

        btn_addtoTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_addtoTest.getText().toString().equals("Thêm câu hỏi vào đề thi"))
                {
                    dialog2.show();
                    setAllQS(0);
                    btn_newquestion.setVisibility(View.GONE);
                    btn_remove.setVisibility(View.GONE);
                    btn_addtoTest.setText("Thêm câu hỏi được chọn vào đề thi");
                }
                else{
                    dialog2.show();
//                    Toast.makeText(getContext(),"THEM VAO TEST",Toast.LENGTH_SHORT).show();
                    addtoTest();

                }

            }
        });
//        cb_addtoTest = (CheckBox) v.findViewById(R.id.cb_addtoTest);


        return v;
    }




    private void setAllQS(Integer TestID){
        if(TestID==0)
            ApiService.apiservice.getAllQues("getAllQS").enqueue(new Callback<AllQues>() {
                @Override
                public void onResponse(Call<AllQues> call, Response<AllQues> response) {
                    dialog2.dismiss();
//                    Toast.makeText(getContext(),"CALL API SUCCESS",Toast.LENGTH_SHORT).show();
                    AllQues allQues = response.body();
                    arrayList = allQues.getAllQues();
//                    for(Question q:arrayList)
//                    dbHandler.addQuestion(q);

//                    Log.e("size",Integer.toString(arrayList.size()));
                    SetAdapter();
                }

                @Override
                public void onFailure(Call<AllQues> call, Throwable t) {
                    dialog2.dismiss();
                    Toast.makeText(getContext(),"Lấy danh sách câu hỏi thất bại",Toast.LENGTH_SHORT).show();
                }
            });
        else{
            ApiService.apiservice.getTest("getTest",Integer.toString(TestID)).enqueue(new Callback<Test>() {
                @Override
                public void onResponse(Call<Test> call, Response<Test> response) {
                    dialog2.dismiss();
//                    Toast.makeText(getContext(),"CALL API SUCCESS",Toast.LENGTH_SHORT).show();
                    Test test = response.body();
                    arrayList = test.getListquestion();
//                    Log.e("size",Integer.toString(arrayList.size()));
                    SetAdapter();
                }

                @Override
                public void onFailure(Call<Test> call, Throwable t) {
                    dialog2.dismiss();
                    Toast.makeText(getContext(),"Lấy danh sách câu hỏi thất bại",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        if else ko chinh sua
        SetAdapter();
        addqsList.clear();
        rmqsList.clear();
    }

    public void SetAdapter(){
        adQuestionAdapter = new ADQuestionAdapter(getContext(),R.layout.fragment_rowquestion,arrayList);
        lv_question = (ListView) getActivity().findViewById(R.id.lv_question);
        lv_question.setAdapter(adQuestionAdapter);
        lv_question.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dialog2.dismiss();

//                current_index=position;
//                adQuestionAdapter.notifyDataSetChanged();{
                if(TestId!=0) {

                    if (btn_addtoTest.getText().toString().equals("Thêm câu hỏi vào đề thi")) {
                        //                        rmqsList.add();
                        int pos = rmqsList.indexOf(Integer.toString(arrayList.get(position).getIdquestion()));
                        Log.e("POS:", Integer.toString(pos));
                        if (pos != -1) {
                            type=-2;
                            rmqsList.remove(pos);

//                            view.setBackgroundColor(Color.parseColor("#fcfcfc"));
                            //                            Log.e("SET BACKGAO R","TRUE");
                        } else {
                            type=2;
                            rmqsList.add(Integer.toString(arrayList.get(position).getIdquestion()));
//                            view.setBackgroundColor(Color.parseColor("#FFF86E6E"));
                        }
                    } else {
                        int pos = addqsList.indexOf(Integer.toString(arrayList.get(position).getIdquestion()));
                        if (pos != -1) {
                            type=-1;
                            addqsList.remove(pos);
//                            view.setBackgroundColor(Color.parseColor("#fcfcfc"));
                        } else {
                            type=1;
//                            view.setBackgroundColor(Color.parseColor("#81C784"));
                            addqsList.add(Integer.toString(arrayList.get(position).getIdquestion()));
                        }
                    }
                    adQuestionAdapter.notifyDataSetChanged(arrayList.get(position).getIdquestion(),type);
                }
                else
                {
                    NavDirections action = questionmanagerDirections.actionQuestionmanagerToInfoquestion(arrayList.get(position));
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(action);
                }
//                    Toast.makeText(getContext(),"da chon" + Integer.toString(arrayList.get(position).getIdquestion())+"testid:"+TestId,Toast.LENGTH_SHORT).show();

                }

        });
        //search item theo id
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Question> filter=new ArrayList<Question>();
                for (Question question:arrayList){
                    if(String.valueOf(question.getIdquestion()).toLowerCase().contains(query.toLowerCase())){
                        filter.add(question);
                    }
                }
                ADQuestionAdapter adapter2=new ADQuestionAdapter(getContext(),R.layout.fragment_rowquestion,filter);
                lv_question.setAdapter(adapter2);
                lv_question.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog2.dismiss();

//                current_index=position;
//                adQuestionAdapter.notifyDataSetChanged();{
                        if(TestId!=0) {

                            if (btn_addtoTest.getText().toString().equals("Thêm câu hỏi vào đề thi")) {
                                //                        rmqsList.add();
                                int pos = rmqsList.indexOf(Integer.toString(arrayList.get(position).getIdquestion()));
                                Log.e("POS:", Integer.toString(pos));
                                if (pos != -1) {
                                    type=-2;
                                    rmqsList.remove(pos);

//                            view.setBackgroundColor(Color.parseColor("#fcfcfc"));
                                    //                            Log.e("SET BACKGAO R","TRUE");
                                } else {
                                    type=2;
                                    rmqsList.add(Integer.toString(arrayList.get(position).getIdquestion()));
//                            view.setBackgroundColor(Color.parseColor("#FFF86E6E"));
                                }
                            } else {
                                int pos = addqsList.indexOf(Integer.toString(filter.get(position).getIdquestion()));
                                if (pos != -1) {
                                    type=-1;
                                    addqsList.remove(pos);
//                            view.setBackgroundColor(Color.parseColor("#fcfcfc"));
                                } else {
                                    type=1;
//                            view.setBackgroundColor(Color.parseColor("#81C784"));
                                    addqsList.add(Integer.toString(filter.get(position).getIdquestion()));
                                }
                            }
                            adapter2.notifyDataSetChanged(filter.get(position).getIdquestion(),type);
                        }
                        else
                        {
                            NavDirections action = questionmanagerDirections.actionQuestionmanagerToInfoquestion(filter.get(position));
                            NavController navController = Navigation.findNavController(view);
                            navController.navigate(action);
                        }
//                    Toast.makeText(getContext(),"da chon" + Integer.toString(arrayList.get(position).getIdquestion())+"testid:"+TestId,Toast.LENGTH_SHORT).show();

                    }

                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Question> filter=new ArrayList<Question>();
                for (Question question:arrayList){
                    if(String.valueOf(question.getIdquestion()).toLowerCase().contains(s.toLowerCase())){
                        filter.add(question);
                    }
                }
                ADQuestionAdapter adapter2=new ADQuestionAdapter(getContext(),R.layout.fragment_rowquestion,filter);
                lv_question.setAdapter(adapter2);
                lv_question.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog2.dismiss();

//                current_index=position;
//                adQuestionAdapter.notifyDataSetChanged();{
                        if(TestId!=0) {

                            if (btn_addtoTest.getText().toString().equals("Thêm câu hỏi vào đề thi")) {
                                //                        rmqsList.add();
                                int pos = rmqsList.indexOf(Integer.toString(filter.get(position).getIdquestion()));
                                Log.e("POS:", Integer.toString(pos));
                                if (pos != -1) {
                                    type=-2;
                                    rmqsList.remove(pos);

//                            view.setBackgroundColor(Color.parseColor("#fcfcfc"));
                                    //                            Log.e("SET BACKGAO R","TRUE");
                                } else {
                                    type=2;
                                    rmqsList.add(Integer.toString(filter.get(position).getIdquestion()));
//                            view.setBackgroundColor(Color.parseColor("#FFF86E6E"));
                                }
                            } else {
                                int pos = addqsList.indexOf(Integer.toString(filter.get(position).getIdquestion()));
                                if (pos != -1) {
                                    type=-1;
                                    addqsList.remove(pos);
//                            view.setBackgroundColor(Color.parseColor("#fcfcfc"));
                                } else {
                                    type=1;
//                            view.setBackgroundColor(Color.parseColor("#81C784"));
                                    addqsList.add(Integer.toString(filter.get(position).getIdquestion()));
                                }
                            }
                            adapter2.notifyDataSetChanged(filter.get(position).getIdquestion(),type);
                        }
                        else
                        {
                            NavDirections action = questionmanagerDirections.actionQuestionmanagerToInfoquestion(filter.get(position));
                            NavController navController = Navigation.findNavController(view);
                            navController.navigate(action);
                        }
//                    Toast.makeText(getContext(),"da chon" + Integer.toString(arrayList.get(position).getIdquestion())+"testid:"+TestId,Toast.LENGTH_SHORT).show();

                    }

                });
                return false;
            }
        });

    }
    private void addtoTest(){
        for(String quesId:addqsList)
        ApiService.apiservice.querryTest("addQStoTest",Integer.toString(TestId),quesId,access_token).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                dialog2.dismiss();
                Log.e("Thanh cong","1 cau hoi");
                setAllQS(TestId);
                btn_addtoTest.setText("Thêm câu hỏi vào đề thi");
                btn_remove.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                dialog2.dismiss();
                Log.e("That bai","1 cau hoi");
            }
        });
        addqsList.clear();
    }
    private void removeQSinTest(){
        dialog2.dismiss();
        if(arrayList.size()-rmqsList.size()>0){
            for(String quesId:rmqsList)
                ApiService.apiservice.querryTest("deleteQSinTest",Integer.toString(TestId),quesId,access_token).enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
//                        Log.e("Thanh cong","1 cau hoi");
                        rmqsList.remove(quesId);
                        if(rmqsList.size()==0) setAllQS(TestId);
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
//                        Log.e("That bai","1 cau hoi");
                    }
                });

            Toast.makeText(getContext(),"Xóa câu hỏi khỏi bài thi thành công",Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(getContext(),"1 bài thi cần tối thiểu 1 câu hỏi",Toast.LENGTH_SHORT).show();

        }

    }


    public ArrayList<String> getAddqsList() {
        return addqsList;
    }

    public ArrayList<String> getRmqsList() {
        return rmqsList;
    }
}