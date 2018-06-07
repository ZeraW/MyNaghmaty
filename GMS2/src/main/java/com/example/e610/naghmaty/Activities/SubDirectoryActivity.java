package com.example.e610.naghmaty.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e610.naghmaty.Models.SubDirectory.Datum;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.ContactUsUtils;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class SubDirectoryActivity extends AppCompatActivity
        {


    String titleDetails, textDetails, logoDetails;
    TextView text, title;
    ImageView logo;
    ImageView faceImg;
    ImageView webImg;
    final String basicImgUrl="http://gms-sms.com:89";

    String face_url;
    String web_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        if( getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

       /* Toolbar toolbar = (Toolbar)  findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
        if( getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/


        setTitle("Subsidiaries");
        Intent intent = getIntent();
        Bundle bundle =intent.getBundleExtra("bundle");
        face_url=bundle.getString("f_url");
        web_url=bundle.getString("w_url");

        Datum data=(Datum) bundle.getSerializable("data");

        int langType= SharedPrefUtils.getSharedPrefValue(this);
        if(langType==1){
            titleDetails = data.getArName();
            textDetails = data.getArDescription();
            setTitle("الشركات الفرعيه");
        }else{
            titleDetails = data.getEnName();
            textDetails = data.getEnDescription();
            setTitle("Subsidiaries");
        }

        logoDetails =basicImgUrl+ data.getLogo();
        text = (TextView) findViewById(R.id.text_products);
        title = (TextView) findViewById(R.id.title_products);
        logo = (ImageView) findViewById(R.id.logo_products);
        faceImg = (ImageView) findViewById(R.id.face_img);
        webImg = (ImageView) findViewById(R.id.web_img);
        Picasso.with(this).load(logoDetails).placeholder(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(logo);
        text.setText(textDetails);
        title.setText(titleDetails);

        if(face_url.equals("none")){
            faceImg.setVisibility(View.GONE);
        }else{
            faceImg.setVisibility(View.VISIBLE);
        }

        if(web_url.equals("none")){
            webImg.setVisibility(View.GONE);
        }else{
            webImg.setVisibility(View.VISIBLE);
        }

        webImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactUsUtils contactUsUtils=new ContactUsUtils(SubDirectoryActivity.this);
                contactUsUtils.openWebsite(web_url);
            }
        });

        faceImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactUsUtils contactUsUtils=new ContactUsUtils(SubDirectoryActivity.this);
                contactUsUtils.openFacebook("",face_url);
            }
        });
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
