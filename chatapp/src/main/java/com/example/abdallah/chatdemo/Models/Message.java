package com.example.abdallah.chatdemo.Models;

import java.io.Serializable;

/**
 * Created by abdallah on 12/6/2017.
 */
public class Message     {

    private User sender;
    private User reciver;
    private String Body;

    public Message( ) {

    }
    public Message(User sender, User reciver, String body) {
        this.sender = sender;
        this.reciver = reciver;
        Body = body;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public User getReciver() {
        return reciver;
    }

    public void setReciver(User reciver) {
        this.reciver = reciver;
    }
}
