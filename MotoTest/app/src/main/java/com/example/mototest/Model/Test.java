package com.example.mototest.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {
        private int Idtest;

        private String Time;
    private ArrayList<Question> Listquestion;

        public Test(int Idtest, ArrayList<Question> ListQuestion, String Time) {
            this.Idtest = Idtest;
            this.Listquestion = ListQuestion;
            this.Time = Time;
        }
    public Test() {
    }


    public int getIdtest() {
            return Idtest;
        }

        public void setIdtest(int idtest) {
            Idtest = idtest;
        }

        public ArrayList<Question> getListquestion() {
            return Listquestion;
        }

        public void setListquestion(ArrayList<Question> listquestion) {
            Listquestion = listquestion;
        }

        public String getTime() {
                return Time;
            }

        public void setTime(String time) {
            Time = time;
        }

}
