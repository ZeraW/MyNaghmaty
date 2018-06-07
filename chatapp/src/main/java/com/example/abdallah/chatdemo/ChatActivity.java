package com.example.abdallah.chatdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.abdallah.chatdemo.Adapters.MessagesAdapter;
import com.example.abdallah.chatdemo.Models.Conversation;
import com.example.abdallah.chatdemo.Models.Message;
import com.example.abdallah.chatdemo.Models.User;
import com.example.abdallah.chatdemo.Utils.Callbacks;
import com.example.abdallah.chatdemo.Utils.Constants;
import com.example.abdallah.chatdemo.Utils.FirebaseDatabasUtils;
import com.example.abdallah.chatdemo.Utils.MySharedPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements Callbacks {

    private RecyclerView recyclerView;
    private MessagesAdapter  messagesAdapter;
    ProgressBar progressBar;

    final String con="Conversations";
    String userType="";
    FloatingActionButton button;
    EditText editText;
    User currentUser;
    User user;
    //ArrayList<User> userList;
    //Conversation  conversation=new Conversation();
    // Write a message to the database
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference mDatabase = database.getReference("database");

    FirebaseDatabasUtils firebaseDatabasUtils;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        MySharedPreferences.setUpMySharedPreferences(this,Constants.constKey);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        button = (FloatingActionButton) findViewById(R.id.btn);
        editText = (EditText) findViewById(R.id.e_txt);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgBody;
                User sender = currentUser;
                User receiver = user;
                if (!TextUtils.isEmpty(editText.getText())) {
                    msgBody = editText.getText().toString().trim();

                    if (userType.equals("u"))
                        firebaseDatabasUtils.sendMessageToAdmin(msgBody, receiver, sender);
                    else {
                        firebaseDatabasUtils.sendMessageToUser(msgBody, receiver, sender);
                    }

                    editText.setText("");
                }
            }
        });

        firebaseDatabasUtils=new FirebaseDatabasUtils();
        firebaseDatabasUtils.addCallbacks(this);


        Intent intent=getIntent();
        Bundle bundle=new Bundle();
        bundle=intent.getBundleExtra("userData");
        userType=bundle.getString("userType");
        if(userType!=null && userType.equals("u")) {
            //sender
            currentUser = new User(bundle.getString("id"), bundle.getString("name"),bundle.getString("img"));
            //receiver
            user=new User("admin","admin","admin");
            //save user
            firebaseDatabasUtils.saveUser(currentUser);
            //mDatabase.child("users").child("user"+currentUser.getUserID()).setValue(currentUser);
        }
        else {
            //receiver
            user= new User(bundle.getString("id"), bundle.getString("name"),bundle.getString("img"));
            //sender
            currentUser=new User("admin","admin","admin");
            if(user!=null&&user.getUserID()!=null) {
                MySharedPreferences.setUserSetting(Constants.unreadMessages+user.getUserID(),"0");
            }
        }


        MySharedPreferences.setUserSetting(Constants.currentUserId,currentUser.getUserID());


        //startSpecialChatService(userType,user,currentUser);

        firebaseDatabasUtils.getConversatiosns(userType,user,currentUser);

        /*//get Users
        userList=new ArrayList<>();
        Query query = mDatabase.child("users");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot s:dataSnapshot.getChildren()) {
                     User user=s.getValue(User.class);
                    userList.add(user);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        //conversation=new Conversation();
       /* Query query1;
        if(userType.equals("u"))
              query1 = mDatabase.child(con).child("admin_"+currentUser.getName()+currentUser.getUserID());
        else {
              query1 = mDatabase.child(con).child("admin_"+user.getName()+user.getUserID());
        }*/

       /* query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("dataSnapshot",dataSnapshot.toString()+"");
                ArrayList<Message>messages=new ArrayList<Message>();
                // using for-each loop for iteration over Map.entrySet()

                for (DataSnapshot ss : dataSnapshot.getChildren() ) {
                    Message m=ss.getValue(Message.class);
                    messages.add(m);
                }
                conversation.setMessageList(messages);
                messagesAdapter = new MessagesAdapter(currentUser,conversation,ChatActivity.this);
                recyclerView = (RecyclerView) findViewById(R.id.chat_recycler);
                recyclerView.setLayoutManager(new GridLayoutManager(ChatActivity.this, 1));
                recyclerView.setAdapter(messagesAdapter);
                recyclerView.scrollToPosition(messages.size()-1);

                //messagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }

   /* private void sendMessageToAdmin(String messageBody,User reciever ,User sender ){
        //if(!TextUtils.isEmpty(editText.getText())) {
            //String messageBody = editText.getText().toString();
           *//* User reciever = new User("admin", "admin","admin");*//*
          *//*  User reciever =user;
            User sender =currentUser;*//*
            Message message = new Message(sender, reciever, messageBody);
            String msgKey=mDatabase.child(con).child("admin_" +sender.getName()+ sender.getUserID()).push().getKey();
            mDatabase.child(con).child("admin_" +sender.getName()+ sender.getUserID()).child("Message"+msgKey).setValue(message);
            editText.setText("");
        //}
    }*/

  /*  private void sendMessageToUser(String messageBody,User reciever ,User sender){
        *//*if(!TextUtils.isEmpty(editText.getText())) {
            String messageBody = editText.getText().toString();
            User sender =currentUser;
            User reciever =user;*//*
            Message message = new Message(sender, reciever , messageBody);
            String msgKey=mDatabase.child(con).child("admin_" +reciever.getName()+ reciever.getUserID()).push().getKey();
            mDatabase.child(con).child("admin_" +reciever.getName()+ reciever.getUserID()).child("Message"+msgKey).setValue(message);
            editText.setText("");
       // }
    }*/


    @Override
    public void OnSuccess(Object object) {

        try {
            progressBar.setVisibility(View.INVISIBLE);
            Conversation conversation = (Conversation) object;
            //MySharedPreferences.setUserSetting(Constants.messagesNumbers,conversation.getMessageList().size()+"");
            messagesAdapter = new MessagesAdapter(currentUser, conversation, ChatActivity.this);
            recyclerView = (RecyclerView) findViewById(R.id.chat_recycler);
            recyclerView.setLayoutManager(new GridLayoutManager(ChatActivity.this, 1));
            recyclerView.setAdapter(messagesAdapter);
            recyclerView.scrollToPosition(conversation.getMessageList().size() - 1);
        }catch (Exception e){}
    }

    @Override
    public void OnFailure(String errorMsg) {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
