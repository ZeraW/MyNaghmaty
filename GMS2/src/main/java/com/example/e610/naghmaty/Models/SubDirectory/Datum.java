
package com.example.e610.naghmaty.Models.SubDirectory;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
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
    @SerializedName("ar_description")
    @Expose
    private String arDescription;
    @SerializedName("en_description")
    @Expose
    private String enDescription;
    @SerializedName("logo")
    @Expose
    private String logo;
    private final static long serialVersionUID = 8943515054983097635L;

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

    public String getArDescription() {
        return arDescription;
    }

    public void setArDescription(String arDescription) {
        this.arDescription = arDescription;
    }

    public String getEnDescription() {
        return enDescription;
    }

    public void setEnDescription(String enDescription) {
        this.enDescription = enDescription;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
