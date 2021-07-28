package com.example.mototest.Api;

import com.example.mototest.Model.Test;
import com.example.mototest.Model.User;

import java.io.Serializable;
import java.util.ArrayList;

public class AllUser implements Serializable {
    private ArrayList<User> allUser;

    public ArrayList<User> getAllUser() {
        return allUser;
    }

    public void setAllUser(ArrayList<User> allUser) {
        this.allUser = allUser;
    }
}
