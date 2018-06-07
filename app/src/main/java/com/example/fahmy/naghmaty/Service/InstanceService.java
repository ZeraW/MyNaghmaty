package com.example.fahmy.naghmaty.Service;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by filipp on 5/23/2016.
 */
public class InstanceService extends FirebaseInstanceIdService {
    public static String token1;

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        saveTokenToPrefs(token);
        Log.e("TAAG", "TOKEN="+token);

    }



    private void saveTokenToPrefs(String _token)
    {
        // Access Shared Preferences
        SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.putString("registration_id1",_token);
        editor.putBoolean("FinalCheckToken",true);
        // Save to SharedPreferences
        editor.apply();
        registertoken();


    }
    public void registertoken() {

        final String token;

        SharedPreferences prefs = getSharedPreferences("token", MODE_PRIVATE);
        Boolean checkToken = prefs.getBoolean("FinalCheckToken", false);
        token=prefs.getString("registration_id1",null);

        if(checkToken){

            //Toast.makeText(getApplicationContext(), "TAAAAAG"+"Hi from here", Toast.LENGTH_SHORT).show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://gms-sms.com:89/naghmaty/api/devices",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //  checkToken = false;
                            Log.e("TAAAAAG", "RESPONSE" + response);
                            SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
                            editor.putBoolean("FinalCheckToken",false);
                            // Save to SharedPreferences
                            editor.apply();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Log.e("TAAAAAG","Token="+ token);

                    params.put("token", token);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    //params.put("Content-Type", "application/json");
                    params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjcyNjU5YWY4NTFhNjdhMTgwYWZlMjdlZjFmNjg4NDQzMmI5YTQ3YmQyYTZjZTM5MjlhZjU0MjEzOTU5ZGNkZTgxZDBlMTA4MzM5OTMzNmYwIn0.eyJhdWQiOiIxIiwianRpIjoiNzI2NTlhZjg1MWE2N2ExODBhZmUyN2VmMWY2ODg0NDMyYjlhNDdiZDJhNmNlMzkyOWFmNTQyMTM5NTlkY2RlODFkMGUxMDgzMzk5MzM2ZjAiLCJpYXQiOjE1MjI3NjQ1OTUsIm5iZiI6MTUyMjc2NDU5NSwiZXhwIjoxNTU0MzAwNTk1LCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.PDmFHXwKiyNi2xtMcWRfe9ftmKLZKr7Ietk0KQQkIJpQx_s3DvZwVps_M8HQVdd_K1KVP33kppZdAMt4xx41sC4YDHGRRxDGMrO2H02BorRvhGujbsLgebV_G8K-KsMcVxlSAxEne_HxNl9HbLcAjYPYXdhgA-rmizI9ny65zHq6aCZ_pwOHHPQg9uKBDoTLB6e7PV2heRbhlo1zHyxyZdUlSjNYL0hmZrlXNmPyCx4RtIGFyiK-xa_-NsUuuBykdfFc_eb8HVPNBQ3wG3ZDvWnX15DbGRlFqNM7lDUfzkE3vKEcpHqT1Nx2qXGSK79wilz2GELhS9O0Mji5N2HO0zAKCG6afHbFohZz94vQaHGukAHThVBrv8q5icmLML9GnuwCJ_W9RUPrWGG-tYPpgHaeGR4BxPl_Ny5t41QOZ5dVkjzvpOaOSmNSu5MJcP8JKS47B32vD5FPdomiAVworHKJC6Tdi4rQr2nhMEMfQW69wW5a8dQL8BrDJWqPksj0zpm0fnJNeLs_2rRJ4gTX62gvwlzI4oqUCmdqA4uhS9u62UbkWU3ywN3_NtJJOwNuQ_jkocKMuJJZj5HXDyM_j-L9h-raccuXLowF_HMldaSMq95NImEoYlRjrV4fyqPfqn48lEomXgQLCFZicw5mn2wL7vz0eYRhkWdVWPJ8oyg");

                    return params;

                }

            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else
        {
            Toast.makeText(this, "Token Was REgisteraion", Toast.LENGTH_SHORT).show();
        }
    }


    public String getTokenFromPrefs()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("registration_id1", null);
    }
}
