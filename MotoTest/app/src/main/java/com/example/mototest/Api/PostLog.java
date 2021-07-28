package com.example.mototest.Api;

public class PostLog {
    private String Username;
    private String Password;

    public PostLog(String username, String password) {
        Username = username;
        Password = password;
    }

    @Override
    public String toString() {
        return "PostLog{" +
                "Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
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
}
