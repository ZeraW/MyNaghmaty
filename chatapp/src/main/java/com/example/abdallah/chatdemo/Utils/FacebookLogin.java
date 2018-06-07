package com.example.abdallah.chatdemo.Utils;

import android.app.Activity;
import android.app.usage.NetworkStats;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.abdallah.chatdemo.ChatActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by abdallah on 12/5/2017.
 */
public class FacebookLogin implements FacebookCallback {

    Activity  activity;
    private List<String> facebookPermissions;
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private ProfileTracker profileTracker =new ProfileTracker() {
        @Override
        protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
            if (currentProfile != null) {
                Intent intent = new Intent(activity, ChatActivity.class);
                Bundle bundle = new Bundle();
                String name = currentProfile.getFirstName() + " " + currentProfile.getLastName();
                bundle.putString("name", name);
                bundle.putString("id", currentProfile.getId());
                intent.putExtra("userData", bundle);
                Toast.makeText(activity, "welcome " + name, Toast.LENGTH_SHORT).show();
                activity.startActivity(intent);
            }
        }
    };
    private AccessTokenTracker accessTokenTracker =new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

        }
    };

    public FacebookLogin(Activity activity){

        this.activity=activity;
        facebookPermissions= Arrays.asList("public_profile","email");
        profileTracker.startTracking();
        accessTokenTracker.startTracking();

    }

    private void initFacebookLogin(){

        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        loginManager=LoginManager.getInstance();
        loginManager.registerCallback(callbackManager,this);

    }

    String userType="";
   public void login( String  type){
       if(NetworkState.ConnectionAvailable(activity)) {
           userType=type;
           initFacebookLogin();
           loginManager.logInWithReadPermissions(activity, facebookPermissions);
       }else
           Toast.makeText(activity,"No Internet Connection",Toast.LENGTH_SHORT).show();
   }

    public void registerCallbackManager(final int requestCode, final int resultCode, final Intent data){
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
        public void onSuccess(Object o) {

        Profile profile=Profile.getCurrentProfile();
        if(profile!=null) {
            Intent intent = new Intent(activity, ChatActivity.class);
            Bundle bundle = new Bundle();
            String name= profile.getFirstName() + " " + profile.getLastName();
            bundle.putString("name",name);
            bundle.putString("id", profile.getId());
            bundle.putString("img", profile.getProfilePictureUri(100,100).toString());
            bundle.putString("userType",userType);
            intent.putExtra("userData", bundle);
            Toast.makeText(activity,"welcome "+name,Toast.LENGTH_SHORT).show();
            activity.startActivity(intent);
        }
       // Toast.makeText(activity,"welcome ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
}
