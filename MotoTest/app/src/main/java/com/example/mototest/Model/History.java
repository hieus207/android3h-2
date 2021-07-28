package com.example.mototest.Model;

public class History {
    private int Iduser;
    private int Idtest;
    private int Scores;
    private long Time;
    public History(int Iduser,int Idtest,int Scores, long Time){
        this.Iduser = Iduser;
        this.Idtest = Idtest;
        this.Scores = Scores;
        this.Time = Time;
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

    public int getScores() {
        return Scores;
    }

    public void setScores(int scores) {
        Scores = scores;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }
}
