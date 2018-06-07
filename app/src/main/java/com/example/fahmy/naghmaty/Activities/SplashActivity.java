package com.example.fahmy.naghmaty.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fahmy.naghmaty.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class SplashActivity extends Activity {
    Button langArabic, langEnglish;
    int checkLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences prefs = getSharedPreferences("lang", MODE_PRIVATE);
        checkLanguage = prefs.getInt("language", 0);

            langArabic = (Button) findViewById(R.id.lang1);
        langEnglish = (Button) findViewById(R.id.lang2);


       if (checkLanguage == 0) {
            langArabic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SplashActivity.this, SplashAboutActivity.class);
                    intent.putExtra("Language", 1);
                    startActivity(intent);
                }
            });
            langEnglish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SplashActivity.this, SplashAboutActivity.class);
                    intent.putExtra("Language", 2);
                    startActivity(intent);
                }
            });
        } else {
            langEnglish.setVisibility(View.INVISIBLE);
            langArabic.setVisibility(View.INVISIBLE);

        }

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent intent = new Intent(getApplicationContext(), SplashAboutActivity.class);
                    if (checkLanguage == 0) {
                        intent.putExtra("Language", 1);

                    } else {
                        intent.putExtra("Language", checkLanguage);


                    }
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
