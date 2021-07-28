package com.example.mototest.Api;

import com.example.mototest.Model.Test;

import java.io.Serializable;
import java.util.ArrayList;

public class Alltest implements Serializable {
    private ArrayList<Test> allTest;

    public ArrayList<Test> getAllTest() {
        return allTest;
    }

    public Alltest(ArrayList<Test> allTest) {
        this.allTest = allTest;
    }

    public void setAllTest(ArrayList<Test> allTest) {
        this.allTest = allTest;
    }
}
