
package com.example.e610.naghmaty.Models.Products;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clients implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<Datum_> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Clients() {
    }

    /**
     * 
     * @param data
     */
    public Clients(List<Datum_> data) {
        super();
        this.data = data;
    }

    public List<Datum_> getData() {
        return data;
    }

    public void setData(List<Datum_> data) {
        this.data = data;
    }

    public int describeContents() {
        return  0;
    }

}
