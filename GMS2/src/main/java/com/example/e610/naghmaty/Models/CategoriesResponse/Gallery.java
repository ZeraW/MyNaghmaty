
package com.example.e610.naghmaty.Models.CategoriesResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Gallery implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("product_id")
    @Expose
    public String productId;
    private final static long serialVersionUID = 4998366638726430573L;

}
