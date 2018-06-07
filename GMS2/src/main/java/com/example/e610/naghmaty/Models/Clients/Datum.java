
package com.example.e610.naghmaty.Models.Clients;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
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
    @SerializedName("products")
    @Expose
    private Products products;
    @SerializedName("services")
    @Expose
    private Services services;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;

    protected Datum(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.arName = ((String) in.readValue((String.class.getClassLoader())));
        this.enName = ((String) in.readValue((String.class.getClassLoader())));
        this.logo = ((String) in.readValue((String.class.getClassLoader())));
        this.products = ((Products) in.readValue((Products.class.getClassLoader())));
        this.services = ((Services) in.readValue((Services.class.getClassLoader())));
    }

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
     * @param services
     * @param enName
     * @param arName
     * @param products
     */
    public Datum(Integer id, String arName, String enName, String logo, Products products, Services services) {
        super();
        this.id = id;
        this.arName = arName;
        this.enName = enName;
        this.logo = logo;
        this.products = products;
        this.services = services;
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

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(arName);
        dest.writeValue(enName);
        dest.writeValue(logo);
        dest.writeValue(products);
        dest.writeValue(services);
    }

    public int describeContents() {
        return  0;
    }

}
