package com.example.fahmy.naghmaty.Fragments;

import android.os.Bundle;

import com.example.fahmy.naghmaty.R;

/**
 * Created by Ahmed Fahmy on 3/21/2018.
 */

public class FragmentUtilites {
    android.support.v4.app.FragmentManager fragmentManager;
    int x;

    public FragmentUtilites(android.support.v4.app.FragmentManager fragmentManager, int x) {
        this.fragmentManager = fragmentManager;
        this.x = x;

    }

    private void addServiceFragment() {
        ServiceFragments serviceFragments = new ServiceFragments();
        Bundle bundle = new Bundle();
        bundle.putInt("languageKey", x);
        serviceFragments.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.linear_service, serviceFragments).commit();
    }

    private void addProjectFragment() {
        ProjectsFragments fragments = new ProjectsFragments();
        Bundle bundle = new Bundle();
        bundle.putInt("languageKey", x);
        fragments.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.linear_Projects, fragments).commit();
    }

    private void addEmployeeFragment() {
        EmployeeFragments fragments = new EmployeeFragments();
        Bundle bundle = new Bundle();
        bundle.putInt("languageKey", x);
        fragments.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.linear_ourteam, fragments).commit();
    }

    public void addAllFragments() {

        addProjectFragment();
        addServiceFragment();
        addEmployeeFragment();

    }
}
