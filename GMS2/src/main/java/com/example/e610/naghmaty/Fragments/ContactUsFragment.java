package com.example.e610.naghmaty.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.ContactUsUtils;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
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


    public Button yes, no;
    ImageView facebookImage;
    ImageView youtubeImage;
    ImageView gmailImage;
    ImageView emailImage;
    ImageView websiteImage;
    ImageView mapImage;
    ImageView phoneImage;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_contact_us, container, false);
        facebookImage = view.findViewById(R.id.facebook_img);
        facebookImage.setOnClickListener(this);
        //facebookImage.setOnTouchListener(this);

        youtubeImage = view.findViewById(R.id.youtube_img);
        youtubeImage.setOnClickListener(this);
        //youtubeImage.setOnTouchListener(this);

        gmailImage = view.findViewById(R.id.google_img);
        gmailImage.setOnClickListener(this);
        //gmailImage.setOnTouchListener(this);


        emailImage = view.findViewById(R.id.mail_img);
        emailImage.setOnClickListener(this);
        //emailImage.setOnTouchListener(this);

        websiteImage = view.findViewById(R.id.website_img);
        websiteImage.setOnClickListener(this);
        //websiteImage.setOnTouchListener(this);


        mapImage = view.findViewById(R.id.map_img);
        mapImage.setOnClickListener(this);
        //mapImage.setOnTouchListener(this);


        phoneImage = view.findViewById(R.id.phone_img);
        phoneImage.setOnClickListener(this);
        //phoneImage.setOnTouchListener(this);
        TextView textView= view.findViewById(R.id.contact_us_title);
        int langType= SharedPrefUtils.getSharedPrefValue(getActivity());
        if(langType==1){
            textView.setText("تواصل معنا");
        }else{
            textView.setText("Contact Us");
        }

        return view;
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
    View v;
    ContactUsUtils contactUsUtils;
    @Override
    public void onClick(View view) {
            contactUsUtils=new ContactUsUtils(getActivity());
            v=view;
        if(v.getId()==R.id.facebook_img){
            contactUsUtils.openFacebook(contactUsUtils.facebookPageID);
        }else if(v.getId()==R.id.youtube_img){
            contactUsUtils.openYoutube(contactUsUtils.youtubeChannel);
        }else if(v.getId()==R.id.google_img){
            contactUsUtils.openWebsite(contactUsUtils.gmailStr);
        }else if(v.getId()==R.id.phone_img){
            contactUsUtils.phoneCall(contactUsUtils.phoneNumber);
        }else if(v.getId()==R.id.mail_img){
            contactUsUtils.openEmail(contactUsUtils.emailStr);
        }else if(v.getId()==R.id.map_img){
            contactUsUtils.openMapLocation(contactUsUtils.locationStr);
        }else if(v.getId()==R.id.website_img){
            contactUsUtils.openWebsite(contactUsUtils.websiteStr);
        }/*
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// Add the buttons
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button

                    if(v.getId()==R.id.facebook_img){
                        contactUsUtils.openFacebook(contactUsUtils.facebookPageID);
                    }else if(v.getId()==R.id.youtube_img){
                        contactUsUtils.openYoutube(contactUsUtils.youtubeChannel);
                    }else if(v.getId()==R.id.google_img){
                        contactUsUtils.openGmail(contactUsUtils.gmailStr);
                    }else if(v.getId()==R.id.phone_img){
                        contactUsUtils.phoneCall(contactUsUtils.phoneNumber);
                    }else if(v.getId()==R.id.mail_img){
                        contactUsUtils.openEmail(contactUsUtils.emailStr);
                    }else if(v.getId()==R.id.map_img){
                        contactUsUtils.openMapLocation(contactUsUtils.locationStr);
                    }else if(v.getId()==R.id.website_img){
                        contactUsUtils.openWebsite(contactUsUtils.websiteStr);
                    }
              *//*  switch (view.getId()) {
                    case R.id.facebook_img:
                        openFacebook(facebookPageID);
                        dismiss();
                        break;
                    case R.id.youtube_img:
                        openYoutube(youtubeChannel);
                        dismiss();
                        break;
                    case R.id.google_img:
                        openGmail(gmailStr);
                        dismiss();
                        break;
                    case R.id.phone_img:
                        phoneCall(phoneNumber);
                        dismiss();
                        break;
                    case R.id.mail_img:
                        openEmail(emailStr);
                        dismiss();
                        break;
                    case R.id.map_img:
                        openMapLocation(locationStr);
                        dismiss();
                        break;
                    case R.id.website_img:
                        openWebsite(websiteStr);
                        dismiss();
                        break;
                    default:
                        break;
                }*//*
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
// Set other dialog properties
            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Do you want continue ?")
                    .setTitle("Confirmation");
// Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();*/
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
