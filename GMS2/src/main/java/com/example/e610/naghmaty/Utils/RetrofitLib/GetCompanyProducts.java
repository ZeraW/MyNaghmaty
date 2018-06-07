package com.example.e610.naghmaty.Utils.RetrofitLib;

import com.example.e610.naghmaty.Models.Products.Products;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 1/11/2018.
 */

public class GetCompanyProducts extends GetData implements Callback<Products> {

    public GetCompanyProducts(){
    }

    public void start() {
        retrofit=ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Products> productsCall = apiInterface.getCompanyProducts();
        productsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Products> call, Response<Products> response) {
        Products products=response.body();
        callbacks.OnSuccess(products);
    }

    @Override
    public void onFailure(Call<Products> call, Throwable t) {
      callbacks.OnFailure(t.getMessage());
    }
}
