package com.example.abdallah.chatdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.abdallah.chatdemo.Utils.Constants;
import com.example.abdallah.chatdemo.Utils.MySharedPreferences;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by abdallah on 12/11/2017.
 *
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Toast.makeText(this,"FirebaseMessaging ^_^",Toast.LENGTH_SHORT).show();
        // Check if message contains a data payload.

        MySharedPreferences.setUpMySharedPreferences(this,Constants.constKey);


            Map<String, String> map = null;
            if (remoteMessage != null)
                map = remoteMessage.getData();
            if (map != null && map.size() > 0) {
           /* Collection<String>strings=map.values();
            String[]strs=null;
            strings.toArray(strs);*/
                String type=map.get("type");
                MySharedPreferences.setUpMySharedPreferences(this, Constants.constKey);
                String currentUserid = MySharedPreferences.getUserSetting(Constants.currentUserId);
                if(type.equals("users")) {
                    if (currentUserid != null && currentUserid.equals("admin")) {
                        String userName=map.get("user_name");
                        String imgUrl=map.get("imgUrl");
                        createNotification(userName,"new user",imgUrl);
                    }
                }else{
                    String userId=map.get("receiver_id");
                    if(userId!=null&&userId.equals(currentUserid)) {
                        String msgBody = map.get("message_body");
                        String sender_name=map.get("sender_name");
                        String sender_id=map.get("sender_id");
                        MySharedPreferences.setUserSetting(Constants.unreadMessages+sender_id,"1");
                        createNotification(msgBody,sender_name,"new message");
                    }

                }
        }
        super.onMessageReceived(remoteMessage);

    }



    private void createNotification( String messageBody ,String contentTitle , String extra ) {
        Intent intent = new Intent( this , ChatMainActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity( this , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);


        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder( this)
                .setSmallIcon(R.mipmap.ic_gms)
                .setContentTitle(contentTitle)
                .setContentText(messageBody)
                .setAutoCancel( true )
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());
    }
    private void chatNotifications(RemoteMessage remoteMessage){
        MySharedPreferences.setUpMySharedPreferences(this,Constants.constKey);


        Map<String, String> map = null;
        if (remoteMessage != null)
            map = remoteMessage.getData();
        if (map != null && map.size() > 0) {
           /* Collection<String>strings=map.values();
            String[]strs=null;
            strings.toArray(strs);*/
            String type=map.get("type");
            MySharedPreferences.setUpMySharedPreferences(this, Constants.constKey);
            String currentUserid = MySharedPreferences.getUserSetting(Constants.currentUserId);
            if(type.equals("users")) {
                if (currentUserid != null && currentUserid.equals("admin")) {
                    String userName=map.get("user_name");
                    String imgUrl=map.get("imgUrl");
                    createNotification(userName,"new user",imgUrl);
                }
            }else{
                String userId=map.get("receiver_id");
                if(userId!=null&&userId.equals(currentUserid)) {
                    String msgBody = map.get("message_body");
                    String sender_name=map.get("sender_name");
                    String sender_id=map.get("sender_id");
                    MySharedPreferences.setUserSetting(Constants.unreadMessages+sender_id,"1");
                    createNotification(msgBody,sender_name,"new message");
                }

            }
        }


    }
}
