package com.example.abdallah.chatdemo.Utils;

import android.util.Log;

import com.example.abdallah.chatdemo.Models.Conversation;
import com.example.abdallah.chatdemo.Models.Message;
import com.example.abdallah.chatdemo.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by abdallah on 12/10/2017.
 */
public class FirebaseDatabasUtils  {

    FirebaseDatabase database ;
    DatabaseReference mDatabase;
    ArrayList<User> userList;
    ArrayList<Callbacks> callbacks;
    final String con="Conversations";
    String userType="";
    Conversation conversation=new Conversation();

    ValueEventListener ConversationsValueEventListener=new ValueEventListener() {
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

            notifyAllSuccessCallbacks(conversation);
            //callbacks.OnSuccess(conversation);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            notifyAllFailureCallbacks(databaseError.getMessage());
        }
    };

    ValueEventListener UsersValueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            userList=new ArrayList<>();
            for (DataSnapshot s:dataSnapshot.getChildren()) {
                User user=s.getValue(User.class);
                userList.add(user);
            }

            //callbacks.OnSuccess(userList);
            notifyAllSuccessCallbacks(userList);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //callbacks.OnFailure(databaseError.getMessage());
            notifyAllFailureCallbacks(databaseError.getMessage());
        }
    };

    public FirebaseDatabasUtils(){
         database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("database");
        callbacks=new ArrayList<>();
    }

    private void notifyAllSuccessCallbacks(Object obj){
        for (Callbacks callback:callbacks) {
            callback.OnSuccess(obj);
        }
    }

    private void notifyAllFailureCallbacks(String error){
        for (Callbacks callback:callbacks) {
            callback.OnFailure(error);
        }
    }

    public void addCallbacks(Callbacks callback){
         callbacks.add(callback);
    }
    public void getUsers(){
        Query query = mDatabase.child("users");
        query.addValueEventListener(UsersValueEventListener);
    }


    public void getConversatiosns(String type , User user, User currentUser){

        Query query1;
        if(type.equals("u"))
            query1 = mDatabase.child(con).child("admin_"+currentUser.getName()+currentUser.getUserID());
        else {
            query1 = mDatabase.child(con).child("admin_"+user.getName()+user.getUserID());
        }
        query1.addValueEventListener(ConversationsValueEventListener);

    }

    public void saveUser(User currentUser){
        mDatabase.child("users").child("user"+currentUser.getUserID()).setValue(currentUser);
    }

    public void sendMessageToAdmin(String messageBody,User reciever ,User sender ){
        //if(!TextUtils.isEmpty(editText.getText())) {
        //String messageBody = editText.getText().toString();
           /* User reciever = new User("admin", "admin","admin");*/
          /*  User reciever =user;
            User sender =currentUser;*/
        Message message = new Message(sender, reciever, messageBody);
        String msgKey=mDatabase.child(con).child("admin_" +sender.getName()+ sender.getUserID()).push().getKey();
        mDatabase.child(con).child("admin_" +sender.getName()+ sender.getUserID()).child("Message"+msgKey).setValue(message);
        //}
    }

    public void sendMessageToUser(String messageBody,User reciever ,User sender){
        /*if(!TextUtils.isEmpty(editText.getText())) {
            String messageBody = editText.getText().toString();
            User sender =currentUser;
            User reciever =user;*/
        Message message = new Message(sender, reciever , messageBody);
        String msgKey=mDatabase.child(con).child("admin_" +reciever.getName()+ reciever.getUserID()).push().getKey();
        mDatabase.child(con).child("admin_" +reciever.getName()+ reciever.getUserID()).child("Message"+msgKey).setValue(message);
        // }
    }


}
