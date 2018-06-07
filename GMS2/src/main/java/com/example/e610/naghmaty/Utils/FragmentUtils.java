package com.example.e610.naghmaty.Utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.e610.naghmaty.Fragments.AboutUsFragment;
import com.example.e610.naghmaty.Fragments.ClientsFragment;
import com.example.e610.naghmaty.Fragments.ContactUsFragment;
import com.example.e610.naghmaty.Fragments.GallaryFragment;
import com.example.e610.naghmaty.Fragments.ProductsFragment;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Fragments.SubFragment;

/**
 * Created by abdallah on 1/17/2018.
 */

public class FragmentUtils {
    FragmentManager fragmentManager;
    public FragmentUtils(FragmentManager fragmentManager){
        this.fragmentManager=fragmentManager;
    }

    public void addContactUsFragment(){
        if(fragmentManager!=null)
            fragmentManager.beginTransaction().add(R.id.contact_us_container,new ContactUsFragment()).commit();
    }

    public void addAboutUsFragment(){
        if(fragmentManager!=null){
            AboutUsFragment aboutUsFragment=new AboutUsFragment();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","main");
            bundle1.putInt("index",0);
            aboutUsFragment.setArguments(bundle1);
            fragmentManager.beginTransaction().add(R.id.about_us_container,aboutUsFragment).commit();

        }
    }

    public void addGalleryFragment(){
        if(fragmentManager!=null){
            GallaryFragment gallaryFragment=new GallaryFragment();
            Bundle bundle=new Bundle();
            bundle.putString("type","main");
            bundle.putInt("index",0);
            gallaryFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.gallary_container,gallaryFragment).commit();
        }
    }

    public void addProductsFragment(){
        if(fragmentManager!=null)
            fragmentManager.beginTransaction().add(R.id.products_container,new ProductsFragment()).commit();
    }

    public void addClientsFragment(){
        if(fragmentManager!=null)
            fragmentManager.beginTransaction().add(R.id.clients_container,new ClientsFragment()).commit();
    }

    public void addSubDirectoryFragment(){
        if(fragmentManager!=null){
            SubFragment  subFragment=new SubFragment();
            Bundle bundle=new Bundle();
            bundle.putString("type","main");
            bundle.putInt("index",0);
            subFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.sub_container,subFragment).commit();
        }
    }


    public void addAllFragment(){
        addContactUsFragment();
        addAboutUsFragment();
        addGalleryFragment();
/*
        addClientsFragment();
*/
        addProductsFragment();
/*
        addSubDirectoryFragment();
*/
    }


}
