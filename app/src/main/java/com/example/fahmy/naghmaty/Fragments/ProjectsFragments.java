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

public class ProjectsFragments extends Fragment {

    View view;
    ProgressDialog pDialog;
    String text_Projects, titleProjects, ima;
    ArrayList<String> imageDetailsProjects = new ArrayList<String>();
    ArrayList<Object> objectProjects = new ArrayList<Object>();
    RecyclerView recyclerViewProjects;
    AdapterRecyclerService adapter;
    TextView title;
    int language;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.projects_fragment, container);
        title = view.findViewById(R.id.titleProjects);
        recyclerViewProjects = (RecyclerView) view.findViewById(R.id.recycler2);

        Bundle bundle = getArguments();
        language = bundle.getInt("languageKey", 1);
        pDialog = new ProgressDialog(getContext());
        vollyProjects(language);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void vollyProjects(final int x) {
        if (x == 1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.gravity = Gravity.RIGHT;
            params.setMargins(30, 15, 30, 30);
            title.setLayoutParams(params);
            title.setText(R.string.Part2_ar);
        } else {
            title.setText(R.string.Part2_en);
        }
       /* if(isDataloaded)
            return;*/
        pDialog.setMessage("Downloading ...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://gms-sms.com:89/naghmaty/api/projects",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                      /*  if (isProjectsDataloaded)
                            return;
                        isProjectsDataloaded = true;*/

                        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(getActivity());
                        dataBaseProcessing.insertToDataBase("projects1", "projects", response);
                        try {

                            JSONObject data = new JSONObject(response);
                            JSONArray array = data.getJSONArray("data");

                            for (int m = 0; m < array.length(); m++) {

                                JSONObject info = array.getJSONObject(m);

                                if (x == 1) {

                                    text_Projects = info.getString("ar_description");
                                    titleProjects = info.getString("ar_name");
                                } else {
                                    text_Projects = info.getString("en_description");
                                    titleProjects = info.getString("en_name");

                                }


                                String image = "http://gms-sms.com:89" + info.getString("logo");

                                JSONArray array2 = info.getJSONArray("images");
                                for (int x = 0; x < array2.length(); x++) {

                                    Log.e("X=", "" + x);


                                    JSONObject object2 = array2.getJSONObject(x);

                                    ima = "http://gms-sms.com:89" + object2.getString("image");


                                    Log.e("MennaProject", ima);

                                    imageDetailsProjects.add(ima);


                                }


                                ModelServices model = new ModelServices(titleProjects, text_Projects, image);
                                objectProjects.add(model);

                            }
                            JSONObject tags = data.getJSONObject("meta");
                         /*   if (x == 1) {
                                tagline = tags.getString("ar_tagline");

                            } else {
                                tagline = tags.getString("en_tagline");

                            }

                            menna.setText(tagline);*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                        recyclerViewProjects.setLayoutManager(mLayoutManager);
                        adapter = new AdapterRecyclerService(getContext(), objectProjects, 3, imageDetailsProjects);
                        recyclerViewProjects.setAdapter(adapter);


                        //initEmployees();

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
                        //Toast.makeText(ProjectsActivity.this, message, Toast.LENGTH_SHORT).show();


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
        //checkInterntetWeaknessProjects();

    }
}
