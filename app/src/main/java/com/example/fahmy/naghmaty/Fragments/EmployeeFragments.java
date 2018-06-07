package com.example.fahmy.naghmaty.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.fahmy.naghmaty.Adapters.AdapterRecyclerOurTeam;
import com.example.fahmy.naghmaty.Constants.DataBaseProcessing;
import com.example.fahmy.naghmaty.Models.ModelOurTeam;
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

public class EmployeeFragments extends Fragment {
    View view;
    TextView title;
    String tilte_service, text_service;
    RecyclerView recyclerViewOurTeam;
    ProgressDialog pDialog;
    AdapterRecyclerOurTeam adapterRecyclerOurTeam;
    int language;
    ArrayList<Object> objectOurTeam = new ArrayList<Object>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.emplyee_fragment, container);
        title = view.findViewById(R.id.titleOurTeam);
        recyclerViewOurTeam=view.findViewById(R.id.recycler3);
        pDialog = new ProgressDialog(getActivity());
        Bundle bundle = getArguments();
        language = bundle.getInt("languageKey", 1);
        initEmployees(language);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    private void initEmployees(final int x) {
        if (x == 1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.gravity = Gravity.RIGHT;
            params.setMargins(30, 15, 30, 30);
            title.setLayoutParams(params);
            title.setText(R.string.Part3_ar);
        } else {
            title.setText(R.string.Part3_en);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://gms-sms.com:89/naghmaty/api/employees",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                       /* if (isTeamsDataloaded)
                            return;
                        isTeamsDataloaded = true;*/

                        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(getActivity());
                        dataBaseProcessing.insertToDataBase("ourteam1", "ourteam", response);
                        try {

                            JSONObject data = new JSONObject(response);
                            JSONArray array = data.getJSONArray("data");

                            for (int m = 0; m < array.length(); m++) {

                                JSONObject info = array.getJSONObject(m);
                                if (x == 1) {

                                    tilte_service = info.getString("ar_name");
                                    text_service = info.getString("ar_position");

                                } else {
                                    tilte_service = info.getString("en_name");
                                    text_service = info.getString("en_position");

                                }
                                String gender = info.getString("gender");
                                String phone = info.getString("phone");


                                ModelOurTeam model = new ModelOurTeam(tilte_service, text_service, gender, phone);
                                objectOurTeam.add(model);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerViewOurTeam.setLayoutManager(linearLayoutManager);
                        adapterRecyclerOurTeam = new AdapterRecyclerOurTeam(getActivity(), objectOurTeam);
                        recyclerViewOurTeam.setAdapter(adapterRecyclerOurTeam);


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
        //checkInterntetWeaknessTeam();

    }
}
