package com.example.mototest.Api;

import com.example.mototest.Model.Question;
import com.example.mototest.Model.Test;

import java.io.Serializable;
import java.util.ArrayList;

public class AllQues implements Serializable {
    private ArrayList<Question> allQS;

    public ArrayList<Question> getAllQues() {
        return allQS;
    }

    public AllQues(ArrayList<Question> allQS) {
        this.allQS = allQS;
    }

    public void setAllQues(ArrayList<Question> allTest) {
        this.allQS = allQS;
    }
}
