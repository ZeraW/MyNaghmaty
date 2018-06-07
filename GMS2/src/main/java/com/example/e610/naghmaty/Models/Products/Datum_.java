
package com.example.e610.naghmaty.Models.Products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datum_ implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ar_name")
    @Expose
    private String arName;
    @SerializedName("en_name")
    @Expose
    private String enName;
    @SerializedName("logo")
    @Expose
    private String logo;




    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum_() {
    }

    /**
     * 
     * @param id
     * @param logo
     * @param enName
     * @param arName
     */
    public Datum_(Integer id, String arName, String enName, String logo) {
        super();
        this.id = id;
        this.arName = arName;
        this.enName = enName;
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int describeContents() {
        return  0;
    }

}
