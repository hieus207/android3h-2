package com.example.mototest.View.Test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mototest.Model.Question;
import com.example.mototest.Model.Test;
import com.example.mototest.Model.Test;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Question> questionList;
    public ViewPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior,ArrayList<Question> list) {
        super(fm, behavior);
        this.questionList =list;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        if(questionList ==null || questionList.isEmpty()){
            return null;
        }
        else
        {
            Question question = questionList.get(position);
            LayoutTestFragment questionFragment=new LayoutTestFragment();
            Bundle bundle=new Bundle();
            bundle.putSerializable("idquestion", (Serializable) question);
            bundle.putInt("position",position+1);
            questionFragment.setArguments(bundle);
            return  questionFragment;
        }
    }

    @Override
    public int getCount() {
        if(questionList !=null){
            return questionList.size();
        }
        return 0;
    }
}
