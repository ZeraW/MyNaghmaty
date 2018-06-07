package com.example.fahmy.naghmaty.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fahmy.naghmaty.Adapters.AdapterRecyclerService;
import com.example.fahmy.naghmaty.Constants.DataBaseProcessing;
import com.example.fahmy.naghmaty.Models.ModelServices;
import com.example.fahmy.naghmaty.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ahmed Fahmy on 3/21/2018.
 */

public class ServiceFragments extends Fragment {

    View view;
    String imaService;
    RecyclerView recyclerViewService;
    AdapterRecyclerService adapter;
    ArrayList<Object> objectService = new ArrayList<Object>();
    ArrayList<String> imageDetailsٍService = new ArrayList<String>();
    ProgressDialog pDialog;
    private String tilte_service, text_service;
    TextView title;
    int language;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.service_fragment, container);

        Bundle bundle = getArguments();
        language = bundle.getInt("languageKey", 1);

        title = view.findViewById(R.id.titleService);
        recyclerViewService = (RecyclerView) view.findViewById(R.id.recycler);
        pDialog = new ProgressDialog(getContext());


        vollyServices(language);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void vollyServices(final int x) {
        if (x == 1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.gravity = Gravity.RIGHT;
            params.setMargins(30, 15, 30, 30);
            title.setLayoutParams(params);
            title.setText(R.string.Part1_ar);
        } else {
            title.setText(R.string.Part1_en);
        }
        pDialog.setMessage("Downloading ...");
        pDialog.show();
      /*  if(isDataloaded)
            return;*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://gms-sms.com:89/naghmaty/api/services",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                     /*   if (isServicesDataloaded)
                            return;
                        isServicesDataloaded = true;*/

                        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(getActivity());
                        dataBaseProcessing.insertToDataBase("service1", "service", response);
                        try {

                            JSONObject data = new JSONObject(response);
                            JSONArray array = data.getJSONArray("data");

                            for (int m = 0; m < array.length(); m++) {

                                JSONObject info = array.getJSONObject(m);
                                if (x == 1) {

                                    tilte_service = info.getString("ar_name");
                                    text_service = info.getString("ar_description");
                                } else {
                                    tilte_service = info.getString("en_name");
                                    text_service = info.getString("en_description");

                                }


                                String logo_service = "http://gms-sms.com:89" + info.getString("logo");
                                Log.e("TAAG1", tilte_service);
                                Log.e("TAAG2", text_service);
                                ModelServices model = new ModelServices(tilte_service, text_service, logo_service);
                                objectService.add(model);

                                JSONArray array2 = info.getJSONArray("images");
                                for (int x = 0; x < array2.length(); x++) {
                                    JSONObject object2 = array2.getJSONObject(x);
                                    imaService = "http://gms-sms.com:89" + object2.getString("image");
                                    Log.e("IMAGE", imaService);
                                    imageDetailsٍService.add(imaService);

                                }
                            }
                            JSONObject tags = data.getJSONObject("meta");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        //gridView.setAdapter(new GridViewAdapter(MainActivity2.this, object));
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
                        recyclerViewService.setLayoutManager(mLayoutManager);
                        adapter = new AdapterRecyclerService(getActivity(), objectService, 1, imageDetailsٍService);
                        recyclerViewService.setAdapter(adapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }
                        //Toast.makeText(ServiceActivity.this, message, Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        //checkInterntetWeaknessServices();
    }

}
