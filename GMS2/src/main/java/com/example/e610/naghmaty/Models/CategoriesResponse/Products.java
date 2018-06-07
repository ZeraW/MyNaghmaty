
package com.example.e610.naghmaty.Models.CategoriesResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum_> data = null;
    private final static long serialVersionUID = 6320978701296701949L;

}
