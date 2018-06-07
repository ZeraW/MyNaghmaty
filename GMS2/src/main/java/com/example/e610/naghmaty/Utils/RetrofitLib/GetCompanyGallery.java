package com.example.e610.naghmaty.Utils.RetrofitLib;

import com.example.e610.naghmaty.Models.Gallery.Gallery;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 1/11/2018.
 */

public class GetCompanyGallery extends GetData implements Callback<Gallery> {

    public GetCompanyGallery(){
    }

    public void start() {
        retrofit=ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Gallery> productsCall = apiInterface.getCompanyGallery();
        productsCall.enqueue(this);
    }


    @Override
    public void onResponse(Call<Gallery> call, Response<Gallery> response) {
        Gallery gallery=response.body();
        callbacks.OnSuccess(gallery);
    }

    @Override
    public void onFailure(Call<Gallery> call, Throwable t) {

        callbacks.OnFailure(t.getMessage());
    }
}
