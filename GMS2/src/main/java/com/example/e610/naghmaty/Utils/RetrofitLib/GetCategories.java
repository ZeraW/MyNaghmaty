package com.example.e610.naghmaty.Utils.RetrofitLib;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.e610.naghmaty.Models.CategoriesResponse.CategoriesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class GetCategories extends GetData implements Callback<CategoriesResponse>  {

    public GetCategories(){
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<CategoriesResponse> clientsCall = apiInterface.getCategories();
        clientsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
        CategoriesResponse clients=response.body();
        callbacks.OnSuccess(clients);
    }

    @Override
    public void onFailure(Call<CategoriesResponse> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
