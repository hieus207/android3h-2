package com.example.mototest.Model;

import java.io.Serializable;

public class User implements Serializable {
    private int Iduser;
    private String Username;
    private String Password;
    private String Name;
    private String Permission;
    private int Active;
    private String Recover;
    private String Access_token;

    public String getAccess_token() {
        return Access_token;
    }

    public void setAccess_token(String access_token) {
        Access_token = access_token;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public String getRecover() {
        return Recover;
    }

    public void setRecover(String recover) {
        Recover = recover;
    }

    public User(int iduser, String username, String password, String name, String permission, int active, String recover) {
        Iduser = iduser;
        Username = username;
        Password = password;
        Name = name;
        Permission = permission;
        Active = active;
        Recover = recover;
    }

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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
