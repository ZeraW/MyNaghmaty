
package com.example.e610.naghmaty.Models.CategoriesResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datum implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("ar_name")
    @Expose
    public String arName;
    @SerializedName("en_name")
    @Expose
    public String enName;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("products")
    @Expose
    public Products products;
    private final static long serialVersionUID = -329112933235162170L;

}
