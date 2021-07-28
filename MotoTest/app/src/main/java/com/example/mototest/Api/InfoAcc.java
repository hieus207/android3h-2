package com.example.mototest.Api;

import android.app.Application;

public class InfoAcc extends Application {
    private int Iduser;
    private String Username;
    private String Name;
    private String Permission;
    private String Access_token;

    public String getAccess_token() {
        return Access_token;
    }

    public void setAccess_token(String access_token) {
        Access_token = access_token;

    }
//    ACCESS TOKEN

    public int getIduser() {
        return Iduser;
    }

    public void setIduser(int iduser) {
        Iduser = iduser;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPermission() {
        return Permission;
    }

    public void setPermission(String permission) {
        Permission = permission;
    }
}
