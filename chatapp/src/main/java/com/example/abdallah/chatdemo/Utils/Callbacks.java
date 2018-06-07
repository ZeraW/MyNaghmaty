package com.example.abdallah.chatdemo.Utils;

import com.example.abdallah.chatdemo.Models.User;

import java.util.ArrayList;

/**
 * Created by abdallah on 12/10/2017.
 */
public interface Callbacks {
    void OnSuccess(Object object);
    void OnFailure(String errorMsg);

}
