package com.example.mototest.Model;

import java.io.Serializable;

public class Question implements Serializable {

    private int Idquestion;
    private String Questionform;
    private String Content;
    private String Image;
    private String Da1;
    private String Da2;
    private String Da3;
    private String Da4;
    private String Dadung;
    public Question(int Idquestion , String Questionform, String Content ,String Image ,String Da1,String Da2,String Da3, String Da4,String Dadung){
        this.Idquestion = Idquestion;
        this.Questionform = Questionform;
        this.Content = Content;
        this.Image = Image;
        this.Da1 = Da1;
        this.Da2 = Da2;
        this.Da3 = Da3;
        this.Da4 = Da4;
        this.Dadung = Dadung;

    }

    public Question() {
        this.Idquestion = -1;
        this.Questionform = "";
        this.Content = "";
        this.Image = "";
        this.Da1 = "";
        this.Da2 = "";
        this.Da3 = "";
        this.Da4 = "";
        this.Dadung = "";
    }
//    public Question(int idquestion) {
//        Idquestion = idquestion;
//    }

    public int getIdquestion() {
        return Idquestion;
    }

    public void setIdquestion(int idquestion) {
        Idquestion = idquestion;
    }

    public String getQuestionform() {
        return Questionform;
    }

    public void setQuestionform(String questionform) {
        Questionform = questionform;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDa1() {
        return Da1;
    }

    public void setDa1(String da1) {
        Da1 = da1;
    }

    public String getDa2() {
        return Da2;
    }

    public void setDa2(String da2) {
        Da2 = da2;
    }

    public String getDa3() {
        return Da3;
    }

    public void setDa3(String da3) {
        Da3 = da3;
    }

    public String getDa4() {
        return Da4;
    }

    public void setDa4(String da4) {
        Da4 = da4;
    }

    public String getDadung() {
        return Dadung;
    }

    public void setDadung(String answer) {
        Dadung = answer;
    }
}
