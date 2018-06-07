
package com.example.e610.naghmaty.Models.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Services implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("meta")
    @Expose
    public Meta meta;
    private final static long serialVersionUID = -5971257596451539962L;

}
