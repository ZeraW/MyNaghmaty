
package com.example.e610.naghmaty.Models.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
    @SerializedName("logo")
    @Expose
    public String logo;
    private final static long serialVersionUID = -1017422150370708682L;

}
