package com.example.mototest.Model;

public class Comment {
    private int Idcmt;
    private int Iduser;
    private int Idtest;
    private String Content;
    private int Like;
    private int DisLike;

    public Comment(int Idcmt, int Iduser, int Idtest, String Content, int Like, int DisLike) {
        this.Idcmt = Idcmt;
        this.Iduser = Iduser;
        this.Idtest = Idtest;
        this.Content = Content;
        this.Like = Like;
        this.DisLike = DisLike;

    }

    public int getIdcmt() {
        return Idcmt;
    }

    public void setIdcmt(int idcmt) {
        Idcmt = idcmt;
    }

    public int getIdtest() {
        return Idtest;
    }

    public void setIdtest(int idtest) {
        Idtest = idtest;
    }

    public int getIduser() {
        return Iduser;
    }

    public void setIduser(int iduser) {
        Iduser = iduser;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getLike() {
        return Like;
    }

    public void setLike(int like) {
        Like = like;
    }

    public int getDisLike() {
        return DisLike;
    }

    public void setDisLike(int disLike) {
        DisLike = disLike;
    }
}
