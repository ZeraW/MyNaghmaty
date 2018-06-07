package com.example.fahmy.naghmaty.Models;

/**
 * Created by Ahmed Fahmy on 12/7/2017.
 */

public class ModelOurTeam {


    String text,title,number,gender;



    public ModelOurTeam(String title , String text, String gender, String number) {
        this.text = text;
        this.title = title;
        this.gender = gender;
        this.number=number;

    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
