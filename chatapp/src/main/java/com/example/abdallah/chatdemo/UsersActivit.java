package com.example.abdallah.chatdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abdallah.chatdemo.Adapters.UsersAdapter;
import com.example.abdallah.chatdemo.Models.User;
import com.example.abdallah.chatdemo.Utils.Callbacks;
import com.example.abdallah.chatdemo.Utils.Constants;
import com.example.abdallah.chatdemo.Utils.FirebaseDatabasUtils;
import com.example.abdallah.chatdemo.Utils.MySharedPreferences;
import com.example.abdallah.chatdemo.Utils.NetworkState;

import java.util.ArrayList;

public class UsersActivit extends AppCompatActivity implements  UsersAdapter.RecyclerViewClickListener ,Callbacks {

    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    ProgressBar progressBar;
    // Write a message to the database
   /*FirebaseDatabase database = FirebaseDatabase.getInstance();
     DatabaseReference mDatabase = database.getReference("database");*/
     ArrayList<User> userList;
    private FirebaseDatabasUtils firebaseDatabasUtils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        MySharedPreferences.setUpMySharedPreferences(this,Constants.constKey);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

      /*  Query query = mDatabase.child("users");
        query.addValueEventListener(this);*/

        if(NetworkState.ConnectionAvailable(this)) {
            firebaseDatabasUtils = new FirebaseDatabasUtils();
            firebaseDatabasUtils.addCallbacks(this);
            firebaseDatabasUtils.getUsers();
        }else
            Toast.makeText(UsersActivit.this,"No Internet Connection",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ItemClicked(View v, int position) {
        String userType="a";
                Intent intent = new Intent(UsersActivit.this, ChatActivity.class);
                Bundle bundle = new Bundle();
                String name=userList.get(position).getName();
                String id=userList.get(position).getUserID();
                String img=userList.get(position).getProfileImg();
                bundle.putString("name",name);
                bundle.putString("id", id);
                bundle.putString("img", img);
                bundle.putString("userType",userType);
                intent.putExtra("userData", bundle);
                Toast.makeText(UsersActivit.this,"welcome admin",Toast.LENGTH_SHORT).show();
                startActivity(intent);
    }


    @Override
    protected void onResume() {

        if(userList!=null&& userList.size()>0) {
            usersAdapter = new UsersAdapter(userList, this);
            recyclerView = (RecyclerView) findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            usersAdapter.setClickListener(this);
            recyclerView.setAdapter(usersAdapter);
        }
        super.onResume();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void OnSuccess(Object object) {
        progressBar.setVisibility(View.INVISIBLE);
        try {
            //User[] users=(User[]) object;
            userList = (ArrayList<User>) object;
            MySharedPreferences.setUserSetting(Constants.usersNumber,userList.size()+"");
            usersAdapter = new UsersAdapter(userList, this);
            recyclerView = (RecyclerView) findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            usersAdapter.setClickListener(this);
            recyclerView.setAdapter(usersAdapter);
        }catch (Exception e){}

    }

    @Override
    public void OnFailure(String errorMsg) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(UsersActivit.this,errorMsg,Toast.LENGTH_SHORT).show();
    }

   /* @Override
    protected void onResume() {
        Intent intent=new Intent(this ,ChatService.class);
        stopService(intent);
        super.onResume();
    }

    @Override
    protected void onPause() {
        Intent intent=new Intent(this ,ChatService.class);
        startService(intent);
        super.onPause();
    }*/


}
