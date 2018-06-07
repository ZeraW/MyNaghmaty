package com.example.e610.naghmaty.Utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * Created by abdallah on 1/15/2018.
 */

public class ContactUsUtils {
    Context c;
    public  final String facebookPageID = "434886903266190";
    public final String youtubeChannel = "https://www.youtube.com/user/Naghmaty/featured";
    public final String gmailStr = "https://plus.google.com/u/0/+Naghmaty";
    public final String phoneNumber = "36864611";
    public final String emailStr = "info@gms-group.company";
    public final String locationStr = "";
    public final String websiteStr = "http://www.gms-group.company/";
    public ContactUsUtils(Context c){
        this.c=c;
    }
    public void openFacebook(String data) {
        Intent intent = getOpenFacebookIntent(c);
        c.startActivity(intent);
    }

    public void openYoutube(String data) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(data));
        c.startActivity(intent);
    }

    public void openGmail(String data) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "abc@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        c.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void phoneCall(String data) {

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
        }else{
            c.startActivity(intent);
        }

     /*   String phone = "01027620565";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        c.startActivity(intent);*/
    }

    public void openMapLocation(String data){
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

    public void openEmail(String data){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",data, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        c.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void openWebsite(String data){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
        c.startActivity(browserIntent);
    }


    public  Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+facebookPageID));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+facebookPageID));
        }
    }

    public  Intent getOpenFacebookIntent(Context context, String id) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+id));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+id));
        }
    }

    public void openFacebook(String data,String id) {
        Intent intent = getOpenFacebookIntent(c,id);
        c.startActivity(intent);
    }
}
