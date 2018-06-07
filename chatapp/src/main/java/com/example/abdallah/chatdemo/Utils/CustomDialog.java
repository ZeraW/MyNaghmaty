package com.example.abdallah.chatdemo.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.abdallah.chatdemo.R;

/**
 * Created by abdallah on 12/14/2017.
 */
public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener, View.OnTouchListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;

    final String facebookPageID = "382277495185350";
    final String youtubeChannel = "https://www.youtube.com/channel/UCmKdSrwf1e8coqAzUsrVHZw";
    final String gmailStr = "https://plus.google.com/110083578209873194520";
    final String phoneNumber = "0236864622";
    final String emailStr = "info@gms-group.company";
    final String locationStr = "";
    final String websiteStr = "http://www.gms-group.company/";


    public CustomDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    ImageView facebookImage;
    ImageView youtubeImage;
    ImageView gmailImage;
    ImageView emailImage;
    ImageView websiteImage;
    ImageView mapImage;
    ImageView phoneImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
     /*   yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);*/

        /**************** make background transparent *********************/
        //make background transparent
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        facebookImage = findViewById(R.id.facebook_img);
        facebookImage.setOnClickListener(this);
        //facebookImage.setOnTouchListener(this);

     /*   youtubeImage = findViewById(R.id.youtube_img);
        youtubeImage.setOnClickListener(this);*/
        //youtubeImage.setOnTouchListener(this);

        gmailImage = findViewById(R.id.google_img);
        gmailImage.setOnClickListener(this);
        //gmailImage.setOnTouchListener(this);


        emailImage = findViewById(R.id.mail_img);
        emailImage.setOnClickListener(this);
        //emailImage.setOnTouchListener(this);

        websiteImage = findViewById(R.id.website_img);
        websiteImage.setOnClickListener(this);
        //websiteImage.setOnTouchListener(this);


        mapImage = findViewById(R.id.map_img);
        mapImage.setOnClickListener(this);
        //mapImage.setOnTouchListener(this);


        phoneImage = findViewById(R.id.phone_img);
        phoneImage.setOnClickListener(this);
        //phoneImage.setOnTouchListener(this);


    }

    // final int facebookId=R.id.facebook_img;

    View view;

    @Override
    public void onClick(View v) {
        view = v;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
// Add the buttons
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button

                if (view.getId() == R.id.facebook_img) {
                    openFacebook(facebookPageID);
                    dismiss();
                /*} else if (view.getId() == R.id.youtube_img) {
                    openYoutube(youtubeChannel);
                    dismiss();*/
                } else if (view.getId() == R.id.google_img) {
                    openGmail(gmailStr);
                    dismiss();
                } else if (view.getId() == R.id.phone_img) {
                    phoneCall(phoneNumber);
                    dismiss();
                } else if (view.getId() == R.id.mail_img) {
                    openEmail(emailStr);
                    dismiss();
                } else if (view.getId() == R.id.map_img) {
                    openMapLocation(locationStr);
                    dismiss();
                } else if (view.getId() == R.id.website_img) {
                    openWebsite(websiteStr);
                    dismiss();
                }
              /*  switch (view.getId()) {
                    case R.id.facebook_img:
                        openFacebook(facebookPageID);
                        dismiss();
                        break;
                    case R.id.youtube_img:
                        openYoutube(youtubeChannel);
                        dismiss();
                        break;
                    case R.id.google_img:
                        openGmail(gmailStr);
                        dismiss();
                        break;
                    case R.id.phone_img:
                        phoneCall(phoneNumber);
                        dismiss();
                        break;
                    case R.id.mail_img:
                        openEmail(emailStr);
                        dismiss();
                        break;
                    case R.id.map_img:
                        openMapLocation(locationStr);
                        dismiss();
                        break;
                    case R.id.website_img:
                        openWebsite(websiteStr);
                        dismiss();
                        break;
                    default:
                        break;
                }*/
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
// Set other dialog properties
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Do you want continue")
                .setTitle("Confirm");
// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

        dismiss();
    }

    private void openFacebook(String data) {
        Intent intent = getOpenFacebookIntent(c);
        c.startActivity(intent);
    }

    private void openYoutube(String data) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(data));
        c.startActivity(intent);
    }

    private void openGmail(String data) {
        /*Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", data, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        c.startActivity(Intent.createChooser(emailIntent, "Send email..."));*/
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
        c.startActivity(browserIntent);
    }

    private void phoneCall(String data) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + data));

        if (ActivityCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
            String phone = "01027620565";
            Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", data, null));
            c.startActivity(intent1);
        } else {
            c.startActivity(intent);
        }

     /*   String phone = "01027620565";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        c.startActivity(intent);*/
    }

    private void openMapLocation(String data) {
     /*   Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
        c.startActivity(intent);*/

/*
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
       // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
       // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");
      // Attempt to start an activity that can handle the Intent
        c.startActivity(mapIntent);*/

        // Creates an Intent that will load a map of San Francisco
        /*Uri gmmIntentUri = Uri.parse("geo:29.9602707,30.9164993");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);*/

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com.eg/maps/place/GMS+Group/@29.9602707,30.9164993,17z/data=!3m1!4b1!4m5!3m4!1s0x14585612acea8d71:0xb03efb016a419bb4!8m2!3d29.9602707!4d30.918688"));
        intent.setPackage("com.google.android.apps.maps");
        c.startActivity(intent);

       /* Intent mapIntent = new Intent(c.getApplicationContext() , MapsActivity.class);
        c.startActivity(mapIntent);*/

    }

    private void openEmail(String data) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", data, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        c.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    private void openWebsite(String data) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
        c.startActivity(browserIntent);
    }


    public Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + facebookPageID));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + facebookPageID));
        }
    }

    int h = 0, w = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // zoomOut(v);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //zoomIn(v);
        }
        return false;
    }


    private void zoomOut(View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        w = view.getLayoutParams().width;
        h = view.getLayoutParams().height;
        view.setLayoutParams(layoutParams);
    }

    private void zoomIn(View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(h, w);
        view.setLayoutParams(layoutParams);
    }

}
