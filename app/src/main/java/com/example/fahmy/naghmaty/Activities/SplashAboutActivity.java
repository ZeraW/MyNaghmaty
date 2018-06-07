package com.example.fahmy.naghmaty.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.fahmy.naghmaty.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class SplashAboutActivity extends Activity {
    String TAG = "SplashAboutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final int x = intent.getIntExtra("Language", 0);
        if (x == 1) {
            setContentView(R.layout.activity_splash_about_ar);
        } else {
            setContentView(R.layout.activity_splash_about_en);

        }


        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("Language", x);
                    SharedPreferences.Editor editor = getSharedPreferences("lang", MODE_PRIVATE).edit();
                    editor.putInt("language", x);
                    editor.apply();

                    Log.e(TAG, "Id=" + x);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
