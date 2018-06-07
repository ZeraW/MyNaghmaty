package com.example.e610.naghmaty.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.e610.naghmaty.Fragments.GallaryFragment;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;

public class GalleryActivity extends AppCompatActivity implements GallaryFragment.OnFragmentInteractionListener
        {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        /*int langType= SharedPrefUtils.getSharedPrefValue(this);
        if(langType==1){}else{}*/

        if( getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        int langType= SharedPrefUtils.getSharedPrefValue(this);
        if(langType==1){
            setTitle("المعرض");
        }
        else{
            setTitle("Gallery");
        }

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");
        Fragment gallaryFragment=new GallaryFragment();
        gallaryFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content,gallaryFragment).commit();
    }

  /*  @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }*/

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
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
