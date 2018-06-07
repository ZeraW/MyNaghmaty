package com.example.abdallah.chatdemo;

import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by abdallah on 12/11/2017.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        //Toast.makeText(this,"FirebaseInstance ^_^",Toast.LENGTH_SHORT).show();
        super.onTokenRefresh();
    }

}
