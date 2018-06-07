package com.example.fahmy.naghmaty.Constants;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.example.fahmy.naghmaty.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ahmed Fahmy on 5/8/2017.
 */

public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Hacen Tunisia Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        //Realm.init(this);

    }
}