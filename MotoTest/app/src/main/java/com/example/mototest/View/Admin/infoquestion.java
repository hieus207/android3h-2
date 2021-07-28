package com.example.mototest.View.Admin;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.Api.ApiService;
import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Api.Status;
import com.example.mototest.MainActivity;
import com.example.mototest.Model.Question;
import com.example.mototest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link infoquestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoquestion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Integer TestId=0;
    Dialog dialog2;
    EditText edt_questionform;
    EditText edt_info_qscontent;
    EditText edt_da1;
    EditText edt_da2;
    EditText edt_da3;
    EditText edt_da4;
    EditText edt_dadung;
    TextView tv_QuesId;
    Button btn_updatequestion,btn_deletequestion,btn_newquestion,btn_imagequestion;
    private String access_token;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public infoquestion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoquestion.
     */
    // TODO: Rename and change types and number of parameters
    public static infoquestion newInstance(String param1, String param2) {
        infoquestion fragment = new infoquestion();
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
//        btn_updatequestion
        View v = inflater.inflate(R.layout.fragment_infoquestion, container, false);
        dialog2=new Dialog(getActivity());
        dialog2.setContentView(R.layout.loading);
        edt_questionform = v.findViewById(R.id.edt_questionform);
        edt_info_qscontent = v.findViewById(R.id.edt_info_qscontent);
        edt_da1 = v.findViewById(R.id.edt_da1);
        edt_da2 = v.findViewById(R.id.edt_da2);
        edt_da3 = v.findViewById(R.id.edt_da3);
        edt_da4 = v.findViewById(R.id.edt_da4);
        edt_dadung = v.findViewById(R.id.edt_dadung);
        tv_QuesId = v.findViewById(R.id.tv_QuesId);
        btn_updatequestion = (Button) v.findViewById(R.id.btn_updatequestion);
        btn_deletequestion = (Button) v.findViewById(R.id.btn_deletequestion);
        btn_newquestion = (Button) v.findViewById(R.id.btn_newquestion);
        btn_imagequestion = (Button) v.findViewById(R.id.btn_imagequestion);
        Question ques = new Question();
        access_token =((InfoAcc) getActivity().getApplication()).getAccess_token();
        try{
//code that may throw an exception
            ques = infoquestionArgs.fromBundle(getArguments()).getQues();
        }catch(Exception e){

        }


//        String qs="";
//        qs=infoquestionArgs.fromBundle(getArguments()).getQSForm();
        if(ques.getIdquestion()==-1){
            btn_imagequestion.setVisibility(View.GONE);
            btn_deletequestion.setVisibility(View.GONE);
            btn_updatequestion.setVisibility(View.GONE);
        }
        else{
            btn_newquestion.setVisibility(View.GONE);
        }

        tv_QuesId.setText(Integer.toString(ques.getIdquestion()));
        edt_questionform.setText(ques.getQuestionform());
        edt_info_qscontent.setText(ques.getContent());
        edt_da1.setText(ques.getDa1());
        edt_da2.setText(ques.getDa2());
        edt_da3.setText(ques.getDa3());
        edt_da4.setText(ques.getDa4());
        edt_dadung.setText(ques.getDadung());

        btn_updatequestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.show();
                updateQS();
            }
        });


        btn_deletequestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.show();
                confirmDel();
            }
        });


        btn_newquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.show();
                createQS();
            }
        });


        return v;
    }

    private void updateQS(){
        dialog2.dismiss();
        ApiService.apiservice.querryQues("updateQS",
                tv_QuesId.getText().toString(),
                edt_questionform.getText().toString(),
                edt_info_qscontent.getText().toString(),
                edt_da1.getText().toString(),
                edt_da2.getText().toString(),
                edt_da3.getText().toString(),
                edt_da4.getText().toString(),
                edt_dadung.getText().toString(),
                access_token
        ).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                Status status = response.body();
                Toast.makeText(getContext(),"Cập nhật câu hỏi thành công",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

                Toast.makeText(getContext(),"Cập nhật thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteQS(){
        dialog2.dismiss();
        ApiService.apiservice.querryQues("deleteQS",
                tv_QuesId.getText().toString(),
                edt_questionform.getText().toString(),
                edt_info_qscontent.getText().toString(),
                edt_da1.getText().toString(),
                edt_da2.getText().toString(),
                edt_da3.getText().toString(),
                edt_da4.getText().toString(),
                edt_dadung.getText().toString(),
                access_token
        ).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                Status status = response.body();
                Toast.makeText(getContext(),"Xóa câu hỏi thành công",Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

                Toast.makeText(getContext(),"Xóa thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createQS(){
        dialog2.dismiss();
        ApiService.apiservice.querryQues("createQS",
                tv_QuesId.getText().toString(),
                edt_questionform.getText().toString(),
                edt_info_qscontent.getText().toString(),
                edt_da1.getText().toString(),
                edt_da2.getText().toString(),
                edt_da3.getText().toString(),
                edt_da4.getText().toString(),
                edt_dadung.getText().toString(),
                access_token
        ).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                Status status = response.body();
                Toast.makeText(getContext(),"Tạo câu hỏi thành công",Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

                Toast.makeText(getContext(),"Tạo thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void confirmDel(){
        dialog2.dismiss();
        Dialog dialog=new Dialog(getActivity());
//        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
        dialog.setContentView(R.layout.dialog_custom);

        Button btn_yes=(Button)dialog.findViewById(R.id.btn_yes);
        Button btn_no=(Button)dialog.findViewById(R.id.btn_no);
        TextView tv_dialog_title= dialog.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_content=dialog.findViewById(R.id.tv_dialog_content);
        tv_dialog_title.setText("Xác nhận Xóa");
        tv_dialog_content.setText("Bạn có chắc muốn xóa câu hỏi này chứ");
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                deleteQS();


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