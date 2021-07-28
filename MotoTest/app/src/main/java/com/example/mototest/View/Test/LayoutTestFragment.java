package com.example.mototest.View.Test;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.Model.Question;
import com.example.mototest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LayoutTestFragment extends Fragment {
    private TextView tvquestitle,tv_da1,tv_da2,tv_da3,tv_da4;
    private LinearLayout layout1;
    private CheckBox checkBox2;
    private CheckBox checkBox1;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private ImageView iv_description;
    private LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    private View v;
    private int stt=0;

//    private String ctl="";
    static ArrayList<String> listans=new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_layout_question, container, false);

        tvquestitle=v.findViewById(R.id.tv_question_title);
        iv_description = v.findViewById(R.id.iv_description);
        layout1=v.findViewById(R.id.layout_answer1);
        checkBox1=v.findViewById(R.id.checkbox1);
        checkBox2=v.findViewById(R.id.checkbox2);
        checkBox3=v.findViewById(R.id.checkbox3);
        checkBox4=v.findViewById(R.id.checkbox4);
        tv_da1=v.findViewById(R.id.tv_answer1_content);
        tv_da2=v.findViewById(R.id.tv_answer2_content);
        tv_da3=v.findViewById(R.id.tv_answer3_content);
        tv_da4=v.findViewById(R.id.tv_answer4_content);
        linearLayout1=v.findViewById(R.id.layout_answer1);
        linearLayout2=v.findViewById(R.id.layout_answer2);
        linearLayout3=v.findViewById(R.id.layout_answer3);
        linearLayout4=v.findViewById(R.id.layout_answer4);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                checkBox1.setChecked(!checkBox1.isChecked());
                ((LayoutTest) getActivity()).setAns(stt,getAns());

            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                checkBox2.setChecked(!checkBox2.isChecked());
                ((LayoutTest) getActivity()).setAns(stt,getAns());
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                checkBox3.setChecked(!checkBox3.isChecked());
                ((LayoutTest) getActivity()).setAns(stt,getAns());
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                checkBox4.setChecked(!checkBox4.isChecked());
                ((LayoutTest) getActivity()).setAns(stt,getAns());
            }
        });
        checkBox1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                ((LayoutTest) getActivity()).setAns(stt,getAns());
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                ((LayoutTest) getActivity()).setAns(stt,getAns());
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                ((LayoutTest) getActivity()).setAns(stt,getAns());
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                ((LayoutTest) getActivity()).setAns(stt,getAns());
            }
        });

        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            Question question = (Question) bundle.get("idquestion");
            Integer position = (Integer) bundle.get("position");
            if(question !=null) {
//                bundle.putString("edttext", "From Fragment");
                tvquestitle.setText(question.getContent());
//                Toast.makeText(getContext(),c4,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(),(String) bundle.get("c4"),Toast.LENGTH_SHORT).show();
                stt=position;
                tv_da1.setText(question.getDa1());
                tv_da2.setText(question.getDa2());
                if(question.getDa2()==null){
                    linearLayout2.setVisibility(View.GONE);
                }
                tv_da3.setText(question.getDa3());
                if(question.getDa3()==null){
                    linearLayout3.setVisibility(View.GONE);
                }
                tv_da4.setText(question.getDa4());
                if(question.getDa4()==null){
                    linearLayout4.setVisibility(View.GONE);
                }
                try {
                    if(!question.getImage().trim().equals(null))
                        Picasso.get().load(question.getImage().trim()).into(iv_description);
                }catch (Exception e){
                    Log.e("Loi","ko tim duoc image");
                }
            }

        }
//        if(((LayoutTest) getActivity()).getisSubmit()){
//            linearLayout1.setBackgroundColor(34);
//            Toast.makeText(getContext(),"da nop bai",Toast.LENGTH_SHORT).show();
//        }
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getAns()!=null){
            ((LayoutTest) getActivity()).setAns(stt,getAns());
        }
        if(((LayoutTest) getActivity()).getisSubmit()){
            String kq=((LayoutTest) getActivity()).getResult(stt-1);
//            LOI CHO NAY
            if (kq.indexOf("1") != -1)
                tv_da1.setTextColor(getResources().getColor(R.color.green));

            if (kq.indexOf("2") != -1)
                tv_da2.setTextColor(getResources().getColor(R.color.green));

            if (kq.indexOf("3") != -1)
                tv_da3.setTextColor(getResources().getColor(R.color.green));

            if (kq.indexOf("4") != -1)
                tv_da4.setTextColor(getResources().getColor(R.color.green));

            if (kq.indexOf("1") == -1 && checkBox1.isChecked())
                tv_da1.setTextColor(getResources().getColor(R.color.red));

            if (kq.indexOf("2") == -1 && checkBox2.isChecked())
                tv_da2.setTextColor(getResources().getColor(R.color.red));

            if (kq.indexOf("3") == -1 && checkBox3.isChecked())
            {
                tv_da3.setTextColor(getResources().getColor(R.color.red));
            }
            if (kq.indexOf("4") == -1 && checkBox4.isChecked())
                tv_da4.setTextColor(getResources().getColor(R.color.red));
        }


    }
    public String getAns(){
        String ctl="";
        if(checkBox1.isChecked())

            ctl+="1";
        if(checkBox2.isChecked()){
            if(ctl!="") ctl+=",";
            ctl+="2";
        }
        if(checkBox3.isChecked()){
            if(ctl!="") ctl+=",";
            ctl+="3";
        }
        if(checkBox4.isChecked()){
            if(ctl!="") ctl+=",";
            ctl+="4";
        }
//        Log.e("stt",ctl);
        return ctl;
    }

}