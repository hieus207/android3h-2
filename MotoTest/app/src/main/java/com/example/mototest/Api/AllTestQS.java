package com.example.mototest.Api;

import com.example.mototest.Model.Test;

import java.util.ArrayList;

public class AllTestQS {
    private ArrayList<TestQS> allTest;

    public AllTestQS(ArrayList<TestQS> allTest) {
        this.allTest = allTest;
    }

    public ArrayList<TestQS> getAllTest() {
        return allTest;
    }

    public void setAllTest(ArrayList<TestQS> allTest) {
        this.allTest = allTest;
    }
}
