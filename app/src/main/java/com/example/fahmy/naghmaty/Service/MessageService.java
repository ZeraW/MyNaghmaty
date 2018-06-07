package com.example.fahmy.naghmaty.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.example.fahmy.naghmaty.Activities.MainActivity;
import com.example.fahmy.naghmaty.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * Created by Quzal on 8/27/2016.
 */
public class MessageService extends FirebaseMessagingService {
    String myborg1;
    Bitmap mIcon_val;
    Boolean check;

    String newsDoc, newsTitle, newsImg, role;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("Samy", "" + remoteMessage.getData());


        if (!remoteMessage.getData().get("type").equals("news")){

            //chatNotifications(remoteMessage);

        }else {

            if (remoteMessage.getData().get("link").equals("")) {
                check = false;
            } else {
                check = true;
            }
            Log.v("checck", check.toString());


            showNotificationLink(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"), remoteMessage.getData().get("link"),
                    remoteMessage.getData().get("img"), check);

            Log.d("ahmed_fahmy", remoteMessage.getData().toString());
        }
    }


    private void showNotificationLink(String title, String message, String url, String img, boolean check1) {


        Log.v("title", title);
        Log.v("title1", message);


        if (check1) {
            Log.v("Check", "No");
         /*   Intent i1 = new Intent(Intent.ACTION_VIEW);
            i1.setData(Uri.parse(url));
            i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i1);*/
     /*       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(browserIntent);
*/


            int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
            try {
                URL newurl = new URL(img);
                mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setLargeIcon(mIcon_val)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setDefaults(Notification.DEFAULT_ALL) // must requires VIBRATE permission
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(m, builder.build());

        } else {


            Log.v("Check", "Yes");
            int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

            try {
                URL newurl = new URL(img);
                mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent i1 = new Intent(this, MainActivity.class);
            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setLargeIcon(mIcon_val)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setDefaults(Notification.DEFAULT_ALL) // must requires VIBRATE permission
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(m, builder.build());


        }

    }


    private void createNotification(String messageBody, String contentTitle, String extra,String type ,String id) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);


        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(contentTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());
    }

  /*  private void chatNotifications(RemoteMessage remoteMessage) {
        MySharedPreferences.setUpMySharedPreferences(this, SyncStateContract.Constants.constKey);


        Map<String, String> map = null;
        if (remoteMessage != null)
            map = remoteMessage.getData();
        if (map != null && map.size() > 0) {
           *//* Collection<String>strings=map.values();
            String[]strs=null;
            strings.toArray(strs);*//*
            String type = map.get("type");
            MySharedPreferences.setUpMySharedPreferences(this, Constants.constKey);
            String currentUserid = MySharedPreferences.getUserSetting(Constants.currentUserId);
            if (type.equals("users")) {
                if (currentUserid != null && currentUserid.equals("admin")) {
                    String userType="a";
                    String userName = map.get("user_name");
                    String userId=map.get("user_id");
                    String imgUrl = map.get("imgUrl");
                    createNotification(userName, "new user", imgUrl ,userType,userId);
                }
            } else {
                String userType="a";
                String userId = map.get("receiver_id");
                if (userId != null && userId.equals(currentUserid)) {
                    String msgBody = map.get("message_body");
                    String sender_name = map.get("sender_name");
                    String sender_id = map.get("sender_id");
                    MySharedPreferences.setUserSetting(Constants.unreadMessages + sender_id, "1");

                    createNotification(msgBody, sender_name, "new message","","");

                }

            }
        }


    }
*/
}
