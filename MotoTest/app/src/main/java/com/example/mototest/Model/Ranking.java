package com.example.mototest.Model;

public class Ranking {
    private int Iduser;
    private int Idtest;
    private String Username;
    private int Scores;
    public Ranking(int Iduser,int Idtest,String Username,int Scores){
        this.Iduser = Iduser;
        this.Idtest = Idtest;
        this.Username = Username;
        this.Scores = Scores;
    }

    public int getIduser() {
        return Iduser;
    }

    public void setIduser(int iduser) {
        Iduser = iduser;
    }

    public int getIdtest() {
        return Idtest;
    }

    public void setIdtest(int idtest) {
        Idtest = idtest;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getScores() {
        return Scores;
    }

    public void setScores(int scores) {
        Scores = scores;
    }
}
