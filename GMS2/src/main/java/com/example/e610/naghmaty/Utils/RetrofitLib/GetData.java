package com.example.e610.naghmaty.Utils.RetrofitLib;

import com.example.e610.naghmaty.Utils.Callbacks;

import retrofit2.Retrofit;

/**
 * Created by abdallah on 1/11/2018.
 */

public class GetData {

    Retrofit retrofit;
    ApiInterface apiInterface;
    Callbacks callbacks;

    public GetData(){}

    public void setCallbacks(Callbacks callbacks){
        this.callbacks=callbacks;
    }

}
