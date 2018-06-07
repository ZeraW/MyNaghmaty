package com.example.fahmy.naghmaty.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.example.abdallah.chatdemo.UsersActivit;
import com.example.abdallah.chatdemo.Utils.FacebookLogin;
import com.example.fahmy.naghmaty.Adapters.AdapterRecyclerOurTeam;
import com.example.fahmy.naghmaty.Adapters.AdapterRecyclerService;
import com.example.fahmy.naghmaty.Constants.DataBaseProcessing;
import com.example.fahmy.naghmaty.Fragments.FragmentUtilites;
import com.example.fahmy.naghmaty.Models.ModelOurTeam;
import com.example.e610.naghmaty.Data.CompanyCategory;
import com.example.fahmy.naghmaty.Models.ModelServices;
import com.example.fahmy.naghmaty.R;
import com.example.e610.naghmaty.Fragments.ClientsFragment;
import com.example.e610.naghmaty.Fragments.ContactUsFragment;
import com.example.e610.naghmaty.Fragments.DetailedFragment;
import com.example.e610.naghmaty.Fragments.GallaryFragment;
import com.example.e610.naghmaty.Fragments.ProductsFragment;
import com.example.e610.naghmaty.Fragments.SubFragment;
import com.example.e610.naghmaty.Utils.FragmentUtils;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , ClientsFragment.OnFragmentInteractionListener
        , ContactUsFragment.OnFragmentInteractionListener, ProductsFragment.OnFragmentInteractionListener
        , GallaryFragment.OnFragmentInteractionListener, DetailedFragment.OnFragmentInteractionListener, SubFragment.OnFragmentInteractionListener {

    RecyclerView recyclerViewService, recyclerViewSubdirectory, recyclerViewProjects, recyclerViewOurTeam;
    LinearLayout linearLayoutProjects, linearServices, linearHome, linearLivechat, linearOurteam;
    RelativeLayout linearProducts, linearClients, linearGallry, linearContactUs, linearAboutUs, linearSubDirectory;
    int x;
    ScrollView scrollView;
    AdapterRecyclerService adapter;
    AdapterRecyclerOurTeam adapterRecyclerOurTeam;
    ProgressDialog pDialog;
    String tilte_service, text_service, title_SubSudiriaus, text_SubSudiriaus, text_Projects, titleProjects, ima, imaService;
    ArrayList<String> imageDetailsٍService = new ArrayList<String>();
    ArrayList<String> imageDetailsProjects = new ArrayList<String>();


    ArrayList<Object> objectService = new ArrayList<Object>();
    ArrayList<Object> objectSubDirectory = new ArrayList<Object>();
    ArrayList<Object> objectProjects = new ArrayList<Object>();
    ArrayList<Object> objectOurTeam = new ArrayList<Object>();


    TextView nameProjects,/* nameServices*/ nameOurTeam;
    ImageView imageChat, livechat;

    private FacebookLogin facebookLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("android");
        FirebaseInstanceId.getInstance().getToken();

        SharedPreferences prefs = getSharedPreferences("lang", MODE_PRIVATE);
        int checkLanguage = prefs.getInt("language", 0);
        x = checkLanguage;
        Log.e("Language", "Language=" + x);


        int langType = SharedPrefUtils.getSharedPrefValue(this);
        if (langType == 1) {

        } else {

        }

        setContentView(R.layout.activity_main);

        initToolBarNavigationDrawer();
        init();

        /*if (x == 1) {
            //nameOurTeam.setText("فريق العمل");
           // nameProjects.setText("المشروعات");
            //nameServices.setText("الخدمات");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.gravity = Gravity.RIGHT;
            params.setMargins(30, 15, 30, 30);
            //nameOurTeam.setLayoutParams(params);
          //  nameProjects.setLayoutParams(params);
            //nameServices.setLayoutParams(params);
            imageChat.setLayoutParams(params);
            livechat.setLayoutParams(params);


        }
*/
        pDialog = new ProgressDialog(this);

        facebookLogin = new FacebookLogin(MainActivity.this);
        linearLivechat.setClickable(true);
        linearLivechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //facebookLogin.login("u");
                Intent intent2 = new Intent(MainActivity.this, UsersActivit.class);
                startActivity(intent2);
            }
        });


        if (isNetworkConnected()) {

            FragmentUtilites fragmentUtilites=new FragmentUtilites(getSupportFragmentManager(),x);
            fragmentUtilites.addAllFragments();


        } else {
            GetDataService();
            //GetDataSubDirectory();
            GetDataProjects();
            GetDataOurTeam();
        }
        FragmentUtils fragmentUtils = new FragmentUtils(getSupportFragmentManager());
        fragmentUtils.addAllFragment();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.id_home) {
            // Handle the camera action
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearHome.getY());
                }
            }, 100);
        } else if (id == R.id.id_products) {
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearProducts.getY());
                }
            }, 100);
        } else if (id == R.id.id_projects) {
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearLayoutProjects.getY());
                }
            }, 100);
        } else if (id == R.id.id_clients) {
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearClients.getY());
                }
            }, 100);
        } else if (id == R.id.id_service) {
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearServices.getY());
                }
            }, 100);
        } else if (id == R.id.id_subsidiaries) {
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearSubDirectory.getY());
                }
            }, 100);
        } else if (id == R.id.id_gallery) {
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearGallry.getY());
                }
            }, 100);

        } else if (id == R.id.id_contactus) {
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearContactUs.getY());
                }
            }, 100);
        } else if (id == R.id.id_ourteam) {
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, (int) linearOurteam.getY());
                }
            }, 100);
        } else if (id == R.id.id_language) {

            SharedPreferences.Editor editor = getSharedPreferences("lang", MODE_PRIVATE).edit();
            // Save to SharedPreferences
            Intent intent1 = new Intent(MainActivity.this, SplashAboutActivity.class);

            if (x == 1) {
                editor.putInt("language", 2);
                intent1.putExtra("Language", 2);
            } else {
                editor.putInt("language", 1);
                intent1.putExtra("Language", 1);

            }
            editor.apply();
            startActivity(intent1);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initToolBarNavigationDrawer() {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.isDrawerIndicatorEnabled();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);

        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.id_language);
        if (x == 1)
            item.setTitle("English");
        else
            item.setTitle("Arabic");
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void init() {

        //gridView = (GridView) findViewById(R.id.gridview);
      /*  text_service1=(TextView)findViewById(R.id.text1_service);
        text_service2=(TextView)findViewById(R.id.text2_service);
        text_service3=(TextView)findViewById(R.id.text3_service);
        text_service4=(TextView)findViewById(R.id.text4_service);

        image_service1=(ImageView) findViewById(R.id.image1_service);
        image_service2=(ImageView) findViewById(R.id.image2_service);
        image_service3=(ImageView) findViewById(R.id.image3_service);
        image_service4=(ImageView) findViewById(R.id.image4_service);
*/

       // nameOurTeam = (TextView) findViewById(R.id.titleOurTeam);
        //nameServices = (TextView) findViewById(R.id.titleService);
        //nameProjects = (TextView) findViewById(R.id.titleProjects);
        imageChat = (ImageView) findViewById(R.id.chaticon);
/*
        livechat = (ImageView) findViewById(R.id.livechat);
*/

        linearLayoutProjects = (LinearLayout) findViewById(R.id.linear_Projects);
        linearServices = (LinearLayout) findViewById(R.id.linear_service);
        linearSubDirectory = (RelativeLayout) findViewById(R.id.sub_container);
        linearHome = (LinearLayout) findViewById(R.id.linear_home);
        linearOurteam = (LinearLayout) findViewById(R.id.linear_ourteam);

        linearLivechat = (LinearLayout) findViewById(R.id.container_livechat);

        linearClients = (RelativeLayout) findViewById(R.id.clients_container);
        linearContactUs = (RelativeLayout) findViewById(R.id.contact_us_container);
        linearGallry = (RelativeLayout) findViewById(R.id.gallary_container);
        linearAboutUs = (RelativeLayout) findViewById(R.id.about_us_container);
        linearProducts = (RelativeLayout) findViewById(R.id.products_container);

       /* btnMoreServices = (Button) findViewById(R.id.btn_MoreService);
        btnMoreProjects = (Button) findViewById(R.id.btn_MoreProjects);*/


        recyclerViewService = (RecyclerView) findViewById(R.id.recycler);
        //recyclerViewSubdirectory = (RecyclerView) findViewById(R.id.recycler1);
        recyclerViewProjects = (RecyclerView) findViewById(R.id.recycler2);
        recyclerViewOurTeam = (RecyclerView) findViewById(R.id.recycler3);


        scrollView = (ScrollView) findViewById(R.id.scrl);

    }

    private void initEmployees() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://gms-sms.com:89/gmsred/api/employees",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        if (isTeamsDataloaded)
                            return;
                        isTeamsDataloaded = true;

                        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(MainActivity.this);
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


                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        recyclerViewOurTeam.setLayoutManager(linearLayoutManager);
                        adapterRecyclerOurTeam = new AdapterRecyclerOurTeam(MainActivity.this, objectOurTeam);
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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        checkInterntetWeaknessTeam();

    }

    private void vollyServices(/*final int x*/) {
        pDialog.setMessage("Downloading ...");
        pDialog.show();
      /*  if(isDataloaded)
            return;*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://gms-sms.com:89/gmsred/api/services",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        if (isServicesDataloaded)
                            return;
                        isServicesDataloaded = true;

                        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(MainActivity.this);
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
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 3);
                        recyclerViewService.setLayoutManager(mLayoutManager);
                        adapter = new AdapterRecyclerService(MainActivity.this, objectService, 1, imageDetailsٍService);
                        recyclerViewService.setAdapter(adapter);
                        vollyProjects();
                        //vollySubdirectory();


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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        checkInterntetWeaknessServices();
    }

    private boolean isServicesDataloaded = false;

    private void checkInterntetWeaknessServices() {

        AsyncTask asyncTask = new AsyncTask() {
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
                if (isServicesDataloaded)
                    return;

                pDialog.hide();
                vollyProjects();
                GetDataService();

                isServicesDataloaded = true;
                super.onPostExecute(o);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private boolean isProjectsDataloaded = false;

    private void checkInterntetWeaknessProjects() {
        AsyncTask asyncTask = new AsyncTask() {
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
                if (isServicesDataloaded)
                    return;

                pDialog.hide();
                GetDataProjects();

                isServicesDataloaded = true;
                super.onPostExecute(o);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private boolean isTeamsDataloaded = false;

    private void checkInterntetWeaknessTeam() {
        AsyncTask asyncTask = new AsyncTask() {
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
                if (isServicesDataloaded)
                    return;
                pDialog.hide();
                GetDataOurTeam();

                isServicesDataloaded = true;
                super.onPostExecute(o);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void vollyProjects() {
       /* if(isDataloaded)
            return;*/
        pDialog.setMessage("Downloading ...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://gms-sms.com:89/gmsred/api/projects",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        if (isProjectsDataloaded)
                            return;
                        isProjectsDataloaded = true;

                        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(MainActivity.this);
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


                                String image = "http://gms-sms.com:89" + info.getString("image");

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
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 3);
                        recyclerViewProjects.setLayoutManager(mLayoutManager);
                        adapter = new AdapterRecyclerService(MainActivity.this, objectProjects, 3, imageDetailsProjects);
                        recyclerViewProjects.setAdapter(adapter);
                        initEmployees();

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
        checkInterntetWeaknessProjects();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        facebookLogin.registerCallbackManager(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void SetDataFromDataBaseService(CompanyCategory model1) {
        try {

            JSONObject data = new JSONObject(model1.getJsonContent());
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
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        recyclerViewService.setLayoutManager(mLayoutManager);
        adapter = new AdapterRecyclerService(MainActivity.this, objectService, 1, imageDetailsٍService);
        recyclerViewService.setAdapter(adapter);
        //vollySubdirectory();

    }

    private void SetDataFromDataBaseProjects(CompanyCategory model1) {
        try {

            JSONObject data = new JSONObject(model1.getJsonContent());
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


                    Log.e("IMAGE", ima);

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
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        recyclerViewProjects.setLayoutManager(mLayoutManager);
        adapter = new AdapterRecyclerService(MainActivity.this, objectProjects, 3, imageDetailsProjects);
        recyclerViewProjects.setAdapter(adapter);

    }

    private void SetDataFromDataBaseSubDirectory(CompanyCategory model1) {
        try {
            JSONObject data = new JSONObject(model1.getJsonContent());
            JSONArray array = data.getJSONArray("data");

            for (int m = 0; m < array.length(); m++) {

                JSONObject info = array.getJSONObject(m);


                if (x == 1) {
                    title_SubSudiriaus = info.getString("ar_name");
                    text_SubSudiriaus = info.getString("ar_description");
                } else {
                    title_SubSudiriaus = info.getString("en_name");
                    text_SubSudiriaus = info.getString("en_description");
                }


                String logo_json = "http://gms-sms.com:89" + info.getString("logo");

                Log.e("TAAG3", logo_json);
                ModelServices model = new ModelServices(title_SubSudiriaus, text_SubSudiriaus, logo_json);
                objectSubDirectory.add(model);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerViewSubdirectory.setLayoutManager(mLayoutManager);
        adapter = new AdapterRecyclerService(MainActivity.this, objectSubDirectory, 2, imageDetailsProjects);
        recyclerViewSubdirectory.setAdapter(adapter);


    }

    private void SetDataFromDataBaseOurTeam(CompanyCategory model1) {
        try {

            JSONObject data = new JSONObject(model1.getJsonContent());
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


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOurTeam.setLayoutManager(linearLayoutManager);
        adapterRecyclerOurTeam = new AdapterRecyclerOurTeam(MainActivity.this, objectOurTeam);
        recyclerViewOurTeam.setAdapter(adapterRecyclerOurTeam);
    }


    private void GetDataService() {

        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(MainActivity.this);
        dataBaseProcessing.selectFromDataBase(MainActivity.this, "service1", "service");
        dataBaseProcessing.setCallbacks(new com.example.e610.naghmaty.Utils.Callbacks() {
            @Override
            public void OnSuccess(Object object) {
                CompanyCategory category = (CompanyCategory) object;
                SetDataFromDataBaseService(category);

            }

            @Override
            public void OnFailure(String errorMsg) {

            }
        });

    }

    private void GetDataProjects() {

        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(MainActivity.this);
        dataBaseProcessing.selectFromDataBase(MainActivity.this, "projects1", "projects");
        dataBaseProcessing.setCallbacks(new com.example.e610.naghmaty.Utils.Callbacks() {
            @Override
            public void OnSuccess(Object object) {
                CompanyCategory category = (CompanyCategory) object;
                SetDataFromDataBaseProjects(category);

            }

            @Override
            public void OnFailure(String errorMsg) {

            }
        });

    }

    private void GetDataOurTeam() {

        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(MainActivity.this);
        dataBaseProcessing.selectFromDataBase(MainActivity.this, "ourteam1", "ourteam");
        dataBaseProcessing.setCallbacks(new com.example.e610.naghmaty.Utils.Callbacks() {
            @Override
            public void OnSuccess(Object object) {
                CompanyCategory category = (CompanyCategory) object;
                SetDataFromDataBaseOurTeam(category);

            }

            @Override
            public void OnFailure(String errorMsg) {

            }
        });

    }

    private void GetDataSubDirectory() {

        DataBaseProcessing dataBaseProcessing = new DataBaseProcessing(MainActivity.this);
        dataBaseProcessing.selectFromDataBase(MainActivity.this, "subdirectory1", "subdirectory");
        dataBaseProcessing.setCallbacks(new com.example.e610.naghmaty.Utils.Callbacks() {
            @Override
            public void OnSuccess(Object object) {
                CompanyCategory category = (CompanyCategory) object;
                SetDataFromDataBaseSubDirectory(category);

            }

            @Override
            public void OnFailure(String errorMsg) {

            }
        });

    }


}


