
package com.example.e610.naghmaty.Models.Clients;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clients implements Parcelable,Serializable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    public final static Creator<Clients> CREATOR = new Creator<Clients>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Clients createFromParcel(Parcel in) {
            return new Clients(in);
        }

        public Clients[] newArray(int size) {
            return (new Clients[size]);
        }

    }
    ;

    protected Clients(Parcel in) {
        in.readList(this.data, (com.example.e610.naghmaty.Models.Clients.Datum.class.getClassLoader()));
        this.meta = ((Meta) in.readValue((Meta.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Clients() {
    }

    /**
     * 
     * @param data
     * @param meta
     */
    public Clients(List<Datum> data, Meta meta) {
        super();
        this.data = data;
        this.meta = meta;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(meta);
    }

    public int describeContents() {
        return  0;
    }

}
