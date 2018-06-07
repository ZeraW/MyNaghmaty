
package com.example.e610.naghmaty.Models.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Clients implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum_> data = null;
    private final static long serialVersionUID = 2323121576880347230L;

}
