
package com.example.e610.naghmaty.Models.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Meta implements Serializable
{

    @SerializedName("ar_tagline")
    @Expose
    public String arTagline;
    @SerializedName("en_tagline")
    @Expose
    public String enTagline;
    private final static long serialVersionUID = 1367693288249686088L;

}
