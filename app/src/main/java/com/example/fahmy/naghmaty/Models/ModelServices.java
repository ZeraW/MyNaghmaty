package com.example.fahmy.naghmaty.Models;

/**
 * Created by Ahmed Fahmy on 12/7/2017.
 */

public class ModelServices {

    String text,title,logo;


    public ModelServices(String title , String text, String logo) {
        this.text = text;
        this.title = title;
        this.logo = logo;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


}
