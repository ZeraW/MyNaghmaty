package com.example.e610.naghmaty.Utils.RetrofitLib;

import com.example.e610.naghmaty.Models.SubDirectory.SubDirectory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 1/11/2018.
 */

public class GetCompanySubDirectory extends GetData implements Callback<SubDirectory> {


    private SubDirectory subDirectory;

    public GetCompanySubDirectory(){
    }

    public void start() {
        retrofit=ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<SubDirectory> productsCall = apiInterface.getCompanySubDirectory();
        productsCall.enqueue(this);


    }


    @Override
    public void onResponse(Call<SubDirectory> call, Response<SubDirectory> response) {
         subDirectory=response.body();
        //SubDirectory subDirectory1=(SubDirectory) JsonParsingUtils.getObject(jsonStr,SubDirectory.class);
        callbacks.OnSuccess(subDirectory);
    }

    @Override
    public void onFailure(Call<SubDirectory> call, Throwable t) {

        callbacks.OnFailure(t.getMessage());
    }
}
