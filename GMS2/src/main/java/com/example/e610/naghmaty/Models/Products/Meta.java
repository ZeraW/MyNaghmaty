
package com.example.e610.naghmaty.Models.Products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Meta implements Serializable
{

    @SerializedName("ar_tagline")
    @Expose
    private String arTagline;
    @SerializedName("en_tagline")
    @Expose
    private String enTagline;


    /**
     * No args constructor for use in serialization
     *
     */
    public Meta() {
    }

    /**
     *
     * @param arTagline
     * @param enTagline
     */
    public Meta(String arTagline, String enTagline) {
        super();
        this.arTagline = arTagline;
        this.enTagline = enTagline;
    }

    public String getArTagline() {
        return arTagline;
    }

    public void setArTagline(String arTagline) {
        this.arTagline = arTagline;
    }

    public String getEnTagline() {
        return enTagline;
    }

    public void setEnTagline(String enTagline) {
        this.enTagline = enTagline;
    }


    public int describeContents() {
        return  0;
    }

}
