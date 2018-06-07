package com.example.e610.naghmaty.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.e610.naghmaty.Fragments.AboutUsFragment;
import com.example.e610.naghmaty.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");
        Fragment aboutUsFragment=new AboutUsFragment();
        aboutUsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.about_us_container,aboutUsFragment).commit();
    }


}
