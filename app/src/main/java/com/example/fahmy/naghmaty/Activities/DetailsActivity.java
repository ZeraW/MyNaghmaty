package com.example.fahmy.naghmaty.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import com.example.fahmy.naghmaty.Models.ModelServices;
import com.example.fahmy.naghmaty.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailsActivity extends AppCompatActivity {
    int m;
    RecyclerView recyclerView;
    ProgressDialog pDialog;
    AdapterRecyclerService adapter;
    String text_Projects, titleProjects, ima;
    ArrayList<Object> objectProjects = new ArrayList<>();
    ArrayList<String> imageDetails = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        m = intent.getIntExtra("Id", 4);
        pDialog = new ProgressDialog(this);
        recyclerView = findViewById(R.id.recycler_Details);

        if (m == 4) {
            setTitle("Services");
            vollyService(m);
        } else {
            setTitle("Projects");
            vollyProjects(m);
        }
    }

    private void vollyProjects(final int x ) {
     /*   if(isDataloaded)
            return;*/
        pDialog.setMessage("Downloading ...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://gms-sms.com:89/naghmaty/api/projects",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();

                   /*     DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(ProjectsActivity.this);
                        dataBaseProcessing.insertToDataBase("projects1", "projects", response);*/
                        try {

                            JSONObject data = new JSONObject(response);
                            JSONArray array = data.getJSONArray("data");

                            for (int m = 0; m < array.length(); m++) {

                                JSONObject info = array.getJSONObject(m);

                              /*  if (x == 1) {
*/
                                text_Projects = info.getString("ar_description");
                                titleProjects = info.getString("ar_name");
                              /*  } else {
                                    String text = info.getString("en_description");
                                    String title = info.getString("en_name");

                                }*/


                                String image = "http://gms-sms.com:89" + info.getString("logo");

                                JSONArray array2 = info.getJSONArray("images");
                                for (int x = 0; x < array2.length(); x++) {
                                    JSONObject object2 = array2.getJSONObject(x);
                                    ima = "http://gms-sms.com:89" + object2.getString("image");


                                }


                                ModelServices model = new ModelServices(titleProjects, text_Projects, ima);
                                objectProjects.add(model);

                            }
                          /*  JSONObject tags = data.getJSONObject("meta");
                            if (x == 1) {
                                tagline = tags.getString("ar_tagline");

                            } else {
                                tagline = tags.getString("en_tagline");

                            }

                            menna.setText(tagline);
*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(DetailsActivity.this, 2);
                        recyclerView.setLayoutManager(mLayoutManager);
                        adapter = new AdapterRecyclerService(DetailsActivity.this, objectProjects, x,imageDetails);
                        recyclerView.setAdapter(adapter);


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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


       /* AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if (isDataloaded)
                    return;

                pDialog.hide();
                DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(ProjectsActivity.this);
                dataBaseProcessing.selectFromDataBase(ProjectsActivity.this, "projects1", "projects");
                dataBaseProcessing.setCallbacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object object) {
                        CompanyCategory category = (CompanyCategory) object;
                        SetDataFromDataBase(category);

                    }

                    @Override
                    public void OnFailure(String errorMsg) {

                    }
                });

                isDataloaded = true;
                super.onPostExecute(o);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
*/

    }

    private void vollyService(final int x) {
     /*   if(isDataloaded)
            return;*/
        pDialog.setMessage("Downloading ...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://gms-sms.com:89/naghmaty/api/services",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();

                   /*     DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(ProjectsActivity.this);
                        dataBaseProcessing.insertToDataBase("projects1", "projects", response);*/
                        try {

                            JSONObject data = new JSONObject(response);
                            JSONArray array = data.getJSONArray("data");

                            for (int m = 0; m < array.length(); m++) {

                                JSONObject info = array.getJSONObject(m);

                              /*  if (x == 1) {
*/
                                text_Projects = info.getString("ar_description");
                                titleProjects = info.getString("ar_name");
                              /*  } else {
                                    String text = info.getString("en_description");
                                    String title = info.getString("en_name");

                                }*/


                                String image = "http://gms-sms.com:89" + info.getString("logo");

                              /*  JSONArray array2 = info.getJSONArray("images");
                                for (int x = 0; x < array2.length(); x++) {
                                    JSONObject object2 = array2.getJSONObject(x);
                                    ima = "http://gms-sms.com:89" + object2.getString("image");


                                }
*/

                                Log.e("TAAG1", image);
                                ModelServices model = new ModelServices(titleProjects, text_Projects, image);
                                objectProjects.add(model);

                            }
                          /*  JSONObject tags = data.getJSONObject("meta");
                            if (x == 1) {
                                tagline = tags.getString("ar_tagline");

                            } else {
                                tagline = tags.getString("en_tagline");

                            }

                            menna.setText(tagline);
*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(DetailsActivity.this, 2);
                        recyclerView.setLayoutManager(mLayoutManager);
                        adapter = new AdapterRecyclerService(DetailsActivity.this, objectProjects,x,imageDetails);
                        recyclerView.setAdapter(adapter);


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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


       /* AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if (isDataloaded)
                    return;

                pDialog.hide();
                DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(ProjectsActivity.this);
                dataBaseProcessing.selectFromDataBase(ProjectsActivity.this, "projects1", "projects");
                dataBaseProcessing.setCallbacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object object) {
                        CompanyCategory category = (CompanyCategory) object;
                        SetDataFromDataBase(category);

                    }

                    @Override
                    public void OnFailure(String errorMsg) {

                    }
                });

                isDataloaded = true;
                super.onPostExecute(o);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
*/

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
