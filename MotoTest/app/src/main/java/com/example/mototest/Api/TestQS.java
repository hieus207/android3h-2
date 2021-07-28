package com.example.mototest.Api;

import com.example.mototest.Model.Question;

import java.io.Serializable;
import java.util.ArrayList;

public class TestQS implements Serializable {
    private int Idtest;
    private String Listquestion;
    private String Time;

    public TestQS(int idtest, String time, String listquestion) {
        Idtest = idtest;
        Time = time;
        Listquestion = listquestion;
    }

    public int getIdtest() {
        return Idtest;
    }

    public void setIdtest(int idtest) {
        Idtest = idtest;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getListquestion() {
        return Listquestion;
    }

    public void setListquestion(String listquestion) {
        Listquestion = listquestion;
    }
}
