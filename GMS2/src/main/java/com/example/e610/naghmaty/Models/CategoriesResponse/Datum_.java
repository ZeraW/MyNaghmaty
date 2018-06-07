
package com.example.e610.naghmaty.Models.CategoriesResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Datum_ implements Serializable
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
    @SerializedName("ar_description")
    @Expose
    public String arDescription;
    @SerializedName("en_description")
    @Expose
    public String enDescription;
    @SerializedName("category_id")
    @Expose
    public String categoryId;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("gallery")
    @Expose
    public List<Gallery> gallery = null;
    private final static long serialVersionUID = 6570359061583788342L;

}
