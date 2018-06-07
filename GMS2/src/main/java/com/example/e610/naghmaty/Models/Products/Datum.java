
package com.example.e610.naghmaty.Models.Products;

import java.io.Serializable;
import java.util.List;

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
    @SerializedName("image")
    @Expose
    private String logo;
    @SerializedName("images")
    @Expose
    private List<Object> images = null;
    @SerializedName("clients")
    @Expose
    private Clients clients;



    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param id
     * @param logo
     * @param enDescription
     * @param enName
     * @param arName
     * @param images
     * @param arDescription
     * @param clients
     */
    public Datum(Integer id, String arName, String enName, String arDescription, String enDescription, String logo, List<Object> images, Clients clients) {
        super();
        this.id = id;
        this.arName = arName;
        this.enName = enName;
        this.arDescription = arDescription;
        this.enDescription = enDescription;
        this.logo = logo;
        this.images = images;
        this.clients = clients;
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

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public int describeContents() {
        return  0;
    }

}
