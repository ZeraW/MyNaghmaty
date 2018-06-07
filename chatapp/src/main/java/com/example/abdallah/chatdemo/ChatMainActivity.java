package com.example.abdallah.chatdemo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abdallah.chatdemo.Utils.Constants;
import com.example.abdallah.chatdemo.Utils.CustomDialog;
import com.example.abdallah.chatdemo.Utils.FacebookLogin;
import com.example.abdallah.chatdemo.Utils.MySharedPreferences;
import com.facebook.login.LoginManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChatMainActivity extends AppCompatActivity {

    FacebookLogin facebookLogin;

    String userType="";

    public void getHashkey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.i("Base64", Base64.encodeToString(md.digest(),Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Name not found", e.getMessage(), e);

        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", e.getMessage(), e);
        }
    }

    String currentUserId="";

    @Override
    public void onBackPressed() {
        CustomDialog customDialog=new CustomDialog(this);
        customDialog.show();

       //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getHashkey();

        FirebaseMessaging.getInstance().subscribeToTopic("android");

        MySharedPreferences.setUpMySharedPreferences(this,Constants.constKey);
        currentUserId=MySharedPreferences.getUserSetting(Constants.currentUserId);
        Log.d("asdasd",currentUserId);

        facebookLogin=new FacebookLogin(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                userType="u";
                facebookLogin.login(userType);

            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                /*userType="a";
                Intent intent = new Intent(ChatMainActivity.this, ChatActivity.class);
                Bundle bundle = new Bundle();
                String name="admin";
                bundle.putString("name",name);
                bundle.putString("id", "admin");
                bundle.putString("img", "admin");
                bundle.putString("userType",userType);
                intent.putExtra("userData", bundle);
                Toast.makeText(ChatMainActivity.this,"welcome "+name,Toast.LENGTH_SHORT).show();
                startActivity(intent);*/

                Intent intent=new Intent(ChatMainActivity.this,UsersActivit.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            facebookLogin.registerCallbackManager(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            LoginManager.getInstance().logOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        /*Intent intent=new Intent(this ,ChatService.class);
        stopService(intent);*/
    /*    if( currentUserId!=null &&!currentUserId.equals("")) {
            Intent intent = new Intent(this, ChatService.class);
            startService(intent);
        }*/
        super.onResume();
    }

    @Override
    protected void onDestroy() {
       // Log.d("asdasd",currentUserId);
       /* if( currentUserId!=null &&!currentUserId.equals("")) {
            Intent intent = new Intent(this, ChatService.class);
            startService(intent);
        }*/
        super.onDestroy();
    }
}
