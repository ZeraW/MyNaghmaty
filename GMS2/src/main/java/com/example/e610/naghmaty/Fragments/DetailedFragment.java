package com.example.e610.naghmaty.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.e610.naghmaty.Models.Products.Products;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.ContactUsUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailedFragment newInstance(String param1, String param2) {
        DetailedFragment fragment = new DetailedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    final String basicImgUrl="http://gms-sms.com:89";
    Bundle bundle;
    Products products;
    int position;
    ImageView socialImg;
    ImageView imageView;
    TextView  bodyTextView;
    TextView titleTextView;
    Toolbar toolbar;
    String img,title,content,socialUrlStr;
    int index=0;

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        view= inflater.inflate(R.layout.fragment_detailed, container, false);


        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity=(AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        if( appCompatActivity.getSupportActionBar()!=null) {
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        imageView = (ImageView) view.findViewById(R.id.cover);
         socialImg = (ImageView) view.findViewById(R.id.img_d);
         bodyTextView = (TextView) view.findViewById(R.id.txt_body_d);
         titleTextView= (TextView) view.findViewById(R.id.txt_d);




        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bundle=getArguments();
        position=bundle.getInt("position");
        //products=(Products)bundle.getSerializable("data");
        socialUrlStr=bundle.getString("url");
        img=basicImgUrl+bundle.getString("img");
        title=bundle.getString("name");
        content=bundle.getString("content");


        Picasso.with(getActivity()).load(img)
                .placeholder(R.drawable.placeholder).error(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(imageView);

        //toolbar.setTitle(title);
        titleTextView.setText(title);

        bodyTextView.setText(content);
        RelativeLayout.LayoutParams params;
        if(!socialUrlStr.contains("http")){
            params=new RelativeLayout.LayoutParams(300,300);
            params.setMargins(30,80,30,30);
            params.addRule(RelativeLayout.BELOW, R.id.txt_body_d);
            socialImg.setLayoutParams(params);
            Picasso.with(getActivity()).load(R.drawable.google_play_logo)
                    .placeholder(R.drawable.placeholder).error(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(socialImg);
        }else {
            Picasso.with(getActivity()).load(R.drawable.webside)
                    .placeholder(R.drawable.placeholder).error(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(socialImg);
              params=new RelativeLayout.LayoutParams(150,150);
              params.setMargins(30,150,30,30);
              params.addRule(RelativeLayout.BELOW, R.id.txt_body_d);
              socialImg.setLayoutParams(params);
        }



        /*if(socialUrlStr.contains("facebook")){
            Picasso.with(getActivity()).load(R.drawable.facebook)
                    .placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(socialImg);
        }else if(socialUrlStr.contains("youtube")){
            Picasso.with(getActivity()).load(R.drawable.youtupe)
                    .placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(socialImg);
        }else if(socialUrlStr.contains("facebook")){

        }else if(socialUrlStr.contains("facebook")){

        }else if(socialUrlStr.contains("facebook")){

        }else{
            Picasso.with(getActivity()).load(R.drawable.webside)
                    .placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(socialImg);
        }*/

        socialImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!socialUrlStr.contains("http")){
                   startGooglePlay(socialUrlStr);
                }else{
                    openWebsite(socialUrlStr);
                }
            }
        });

        return view;
    }

    private void startGooglePlay(String str){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + str)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
        }
    }

    private void openWebsite(String str){
        ContactUsUtils contactUsUtils=new ContactUsUtils(getActivity());
        contactUsUtils.openWebsite(str);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
