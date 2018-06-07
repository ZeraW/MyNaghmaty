package com.example.fahmy.naghmaty.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fahmy.naghmaty.R;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class DetailsProjectsActivity extends AppCompatActivity {
    String titleDetails, textDetails, logoDetails;
    TextView text, title;
    ImageView logo, website, youtube, facebook;

    int x, postition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_projects);
        init();

        Intent intent = getIntent();
        x = intent.getIntExtra("Position", 1);
        postition = intent.getIntExtra("pos", 0);
        titleDetails = intent.getStringExtra("Modeltitle");
        textDetails = intent.getStringExtra("Modeltext");
        logoDetails = intent.getStringExtra("Modellogo");





        if (x == 1) {
            int langType= SharedPrefUtils.getSharedPrefValue(this);
            if(langType==1) {
                setTitle("الخدمات");
            }else{
                setTitle("Services");
            }

        }

        if (x == 3) {
            int langType= SharedPrefUtils.getSharedPrefValue(this);
            if(langType==1) {
                setTitle("المشروعات");
            }else{
                setTitle("Projects");
            }

            website.setVisibility(View.GONE);
            if (postition == 0) {

                facebook.setVisibility(View.VISIBLE);
                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent facebookIntent = getOpenFacebookIntent(DetailsProjectsActivity.this, "1639260106090208", "https://www.facebook.com/studio.online.talant/");
                        startActivity(facebookIntent);
                    }
                });
                youtube.setVisibility(View.VISIBLE);
                youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openYoutubeChannel("https://www.youtube.com/channel/UCnRvK2vVPQTWtiuLVd_exng");
                    }
                });


            } else if (postition == 1) {
                youtube.setVisibility(View.GONE);
                facebook.setVisibility(View.GONE);
                website.setVisibility(View.VISIBLE);
                website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openWebSite("http://www.motrebtone.com/");
                    }
                });

            } else if (postition == 3) {

                website.setVisibility(View.GONE);
                facebook.setVisibility(View.VISIBLE);
                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent facebookIntent = getOpenFacebookIntent(DetailsProjectsActivity.this, "434886903266190", "https://www.facebook.com/naghmaty/");
                        startActivity(facebookIntent);
                    }
                });
                youtube.setVisibility(View.VISIBLE);
                youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openYoutubeChannel("https://www.youtube.com/user/Naghmaty");
                    }
                });


            } else if (postition == 2) {

                website.setVisibility(View.GONE);
                facebook.setVisibility(View.VISIBLE);
                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent facebookIntent = getOpenFacebookIntent(DetailsProjectsActivity.this, "277131322738776", "https://www.facebook.com/");
                        startActivity(facebookIntent);
                    }
                });
                youtube.setVisibility(View.VISIBLE);
                youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openYoutubeChannel("https://www.youtube.com/channel/UCgIsivzaABcSKovGiHvHgCw");
                    }
                });
            } else if (postition == 4) {

                youtube.setVisibility(View.GONE);
                facebook.setVisibility(View.VISIBLE);
                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent facebookIntent = getOpenFacebookIntent(DetailsProjectsActivity.this, "263106774137984", "https://www.facebook.com/IdeaGate.eg/");
                        startActivity(facebookIntent);
                    }
                });

                website.setVisibility(View.VISIBLE);
                website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openWebSite("http://idea-gate.com/");

                    }
                });


            }
        }


        text = (TextView) findViewById(R.id.text_products);
        title = (TextView) findViewById(R.id.title_products);
        logo = (ImageView) findViewById(R.id.logo_products);


        Picasso.with(this).load(logoDetails).placeholder(R.drawable.load).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(logo);
        text.setText(textDetails);
        title.setText(titleDetails);


    }

    private void init() {
        youtube = (ImageView) findViewById(R.id.youtube);
        facebook = (ImageView) findViewById(R.id.facebook);
        website = (ImageView) findViewById(R.id.website);

    }

    /*   @Override
       protected void attachBaseContext(Context newBase) {
           super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
       }*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    private void openYoutubeChannel(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }


    private void openWebSite(String link) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }


    public static Intent getOpenFacebookIntent(Context context, String facebookId, String link) {


        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/" + facebookId)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(link)); //catches and opens a url to the desired page
        }
    }
}

