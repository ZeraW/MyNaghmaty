package com.example.abdallah.chatdemo.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abdallah on 12/6/2017.
 */
public class Conversation implements Parcelable {

    ArrayList<Message> messageList;
    String conID;

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    public String getConID() {
        return conID;
    }

    public void setConID(String conID) {
        this.conID = conID;
    }

    public Conversation( ) {
    }

    public Conversation(ArrayList<Message> messageList, String conID) {
        this.messageList = messageList;
        this.conID = conID;
    }

    protected Conversation(Parcel in) {
        if (in.readByte() == 0x01) {
            messageList = new ArrayList<Message>();
            in.readList(messageList, Message.class.getClassLoader());
        } else {
            messageList = null;
        }
        conID = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (messageList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(messageList);
        }
        dest.writeString(conID);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Conversation> CREATOR = new Parcelable.Creator<Conversation>() {
        @Override
        public Conversation createFromParcel(Parcel in) {
            return new Conversation(in);
        }

        @Override
        public Conversation[] newArray(int size) {
            return new Conversation[size];
        }
    };
}