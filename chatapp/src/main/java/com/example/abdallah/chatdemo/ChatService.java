package com.example.abdallah.chatdemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.abdallah.chatdemo.Models.Conversation;
import com.example.abdallah.chatdemo.Models.Message;
import com.example.abdallah.chatdemo.Models.User;
import com.example.abdallah.chatdemo.Utils.Callbacks;
import com.example.abdallah.chatdemo.Utils.Constants;
import com.example.abdallah.chatdemo.Utils.FirebaseDatabasUtils;
import com.example.abdallah.chatdemo.Utils.MySharedPreferences;

import java.util.ArrayList;

public class ChatService extends Service     {

    static int notificationId=1001;
    FirebaseDatabasUtils firebaseDatabasUtils;

    Callbacks UsersCallBack=new Callbacks() {
        @SuppressWarnings("unchecked")
        @Override
        public void OnSuccess(Object object) {

            if (currentUserId.equals("admin")) {
                //Log.d("asdasd","asd_");
                try {
                    int usersNumber = Integer.valueOf(MySharedPreferences.getUserSetting(Constants.usersNumber));
                    ArrayList<User> list = (ArrayList<User>) object;
                    if (usersNumber < list.size()) {
                        User user =list.get(0);
                        MySharedPreferences.setUserSetting(Constants.usersNumber, list.size() + "");
                        PendingIntent pendingIntent = createPendingIntent();
                        Notification notification = createNotification(pendingIntent,user);
                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(notificationId++, notification);
                    } else
                        MySharedPreferences.setUserSetting(Constants.usersNumber, list.size() +"");

                } catch (Exception e) {}
               // startService(new Intent(ChatService.this, ChatService.class));
            }

        }

        @Override
        public void OnFailure(String errorMsg) {

        }
    };

    Callbacks ConversationsCallBack = new Callbacks() {
        @Override
        public void OnSuccess(Object object) {
            Log.d("asd1","1");
            Toast.makeText(ChatService.this,"1",Toast.LENGTH_SHORT).show();
            try {
                Conversation conversation = (Conversation) object;
                String s=conversation.getMessageList().get(conversation.getMessageList().size()-1).getReciver().getName();
                Toast.makeText(ChatService.this,s,Toast.LENGTH_SHORT).show();
                if ( currentUserId.equals(s)) {
                    Log.d("asd2","2");
                    Toast.makeText(ChatService.this,"2",Toast.LENGTH_SHORT).show();
                    int msgNumbers=Integer.valueOf(MySharedPreferences.getUserSetting(Constants.messagesNumbers));
                    if(msgNumbers<conversation.getMessageList().size()){
                        Log.d("asd3","3");
                        Toast.makeText(ChatService.this,"3",Toast.LENGTH_SHORT).show();
                        Message message=conversation.getMessageList().get(conversation.getMessageList().size()-1);
                        MySharedPreferences.setUserSetting(Constants.messagesNumbers, conversation.getMessageList().size() + "");
                        PendingIntent pendingIntent = createPendingIntent();
                        Notification notification = createNotification(pendingIntent,message);
                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(notificationId++, notification);
                    }else
                        MySharedPreferences.setUserSetting(Constants.messagesNumbers, conversation.getMessageList().size() + "");
                    //startSpecialChatService(userType,receiver,sender);
                }


            } catch (Exception e) {}
        }

        @Override
        public void OnFailure(String errorMsg) {

        }
    };


    private void startSpecialChatService(String userType, User receiver,User sender){
        Intent intent=new Intent(this,ChatService.class);
        Bundle bundle=new Bundle();
        bundle.putString(Constants.userType,userType);
        bundle.putParcelable(Constants.sender,sender);
        bundle.putParcelable(Constants.receiver,receiver);
        intent.putExtra(Constants.bundle,bundle);
        intent.setAction(Constants.messagesAction);
        startService(intent);
    }

    public ChatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    String currentUserId;
    User sender=new User();
    User receiver=new User();
    String userType="";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        MySharedPreferences.setUpMySharedPreferences(this,Constants.constKey);
        firebaseDatabasUtils=new FirebaseDatabasUtils();
        firebaseDatabasUtils.addCallbacks(ConversationsCallBack);
        firebaseDatabasUtils.addCallbacks(UsersCallBack);
        firebaseDatabasUtils.getUsers();
        currentUserId = MySharedPreferences.getUserSetting(Constants.currentUserId);
        firebaseDatabasUtils.getConversatiosns(userType,receiver,sender);
        String action="";
        if(intent!=null)
            action=intent.getAction();
        if(action!=null&&action.equals(Constants.messagesAction)){
            Bundle bundle=intent.getBundleExtra(Constants.bundle);
            if(bundle!=null) {
                  sender = bundle.getParcelable(Constants.sender);
                  receiver = bundle.getParcelable(Constants.receiver);
                  userType=bundle.getString(Constants.userType);
                  //firebaseDatabasUtils.getConversatiosns(userType,receiver,sender);
            }
        }

        return START_STICKY;
    }

    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(this, UsersActivit.class);
        return PendingIntent.getActivity(this,8976, intent, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification createNotification(PendingIntent intent,User user) {
        return new Notification.Builder(this)
                .setContentTitle("You have a new user ")
                .setContentText(user.getName())
                .setSmallIcon(R.drawable.facebook_circle)
                .setContentIntent(intent)
                .build();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification createNotification(PendingIntent intent,Message message) {
        return new Notification.Builder(this)
                .setContentTitle("a new message from "+message.getSender().getName())
                .setContentText(message.getBody())
                .setSmallIcon(R.drawable.facebook_circle)
                .setContentIntent(intent)
                .build();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
