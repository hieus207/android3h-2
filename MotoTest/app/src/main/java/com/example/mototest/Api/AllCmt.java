package com.example.mototest.Api;

import com.example.mototest.Model.Comment;
import com.example.mototest.Model.User;


import java.io.Serializable;
import java.util.ArrayList;

public class AllCmt implements Serializable {
    private ArrayList<Comment> allCmt;
    private ArrayList<User> infoCmt;

    public ArrayList<User> getInfoCmt() {
        return infoCmt;
    }

    public void setInfoCmt(ArrayList<User> infoCmt) {
        this.infoCmt = infoCmt;
    }

    public AllCmt(ArrayList<Comment> allCmt) {
        this.allCmt = allCmt;
    }

    public ArrayList<Comment> getAllCmt() {
        return allCmt;
    }

    public void setAllCmt(ArrayList<Comment> allCmt) {
        this.allCmt = allCmt;
    }
}
