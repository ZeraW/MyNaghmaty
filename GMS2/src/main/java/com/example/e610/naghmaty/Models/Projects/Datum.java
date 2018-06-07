
package com.example.e610.naghmaty.Models.Projects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

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
    @SerializedName("ar_description")
    @Expose
    public String arDescription;
    @SerializedName("en_description")
    @Expose
    public String enDescription;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("images")
    @Expose
    public List<Object> images = null;
    private final static long serialVersionUID = -676943524888954011L;

}
