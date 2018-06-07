package com.example.e610.naghmaty.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.e610.naghmaty.Fragments.ClientsFragment;
import com.example.e610.naghmaty.Fragments.ContactUsFragment;
import com.example.e610.naghmaty.Fragments.GallaryFragment;
import com.example.e610.naghmaty.Fragments.ProductsFragment;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Fragments.ViewPagerFragment1;


public class MainActivity2 extends AppCompatActivity implements ClientsFragment.OnFragmentInteractionListener
        ,ProductsFragment.OnFragmentInteractionListener, GallaryFragment.OnFragmentInteractionListener

        ,ContactUsFragment.OnFragmentInteractionListener,ViewPagerFragment1.OnFragmentInteractionListener {


    private EditText editTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //editTxt=(EditText)findViewById(R.id.e);
     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                *//*String s=editTxt.getText().toString();
                Toast.makeText(MainActivity2.this,s,Toast.LENGTH_SHORT).show();*//*

               *//* getSupportFragmentManager().beginTransaction().add(R.id.clients_container,new Fragment1()).commit();
                getSupportFragmentManager().beginTransaction().add(R.id.products_container,new ProductsFragment()).commit();
                getSupportFragmentManager().beginTransaction().add(R.id.contact_us_container,new ContactUsFragment()).commit();

                GallaryFragment gallaryFragment=new GallaryFragment();
                Bundle bundle=new Bundle();
                bundle.putString("type","main");
                bundle.putInt("index",0);
                gallaryFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.gallary_container,gallaryFragment).commit();

                AboutUsFragment  aboutUsFragment=new AboutUsFragment();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","main");
                bundle1.putInt("index",0);
                aboutUsFragment.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().add(R.id.about_us_container,aboutUsFragment).commit();*//*

                FragmentUtils fragmentUtils=new FragmentUtils(getSupportFragmentManager());
                fragmentUtils.addAllFragment();

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
