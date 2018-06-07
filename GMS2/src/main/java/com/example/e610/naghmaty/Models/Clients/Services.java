
package com.example.e610.naghmaty.Models.Clients;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Services implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum__> data = null;
    public final static Creator<Services> CREATOR = new Creator<Services>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Services createFromParcel(Parcel in) {
            return new Services(in);
        }

        public Services[] newArray(int size) {
            return (new Services[size]);
        }

    }
    ;

    protected Services(Parcel in) {
        in.readList(this.data, (com.example.e610.naghmaty.Models.Clients.Datum__.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Services() {
    }

    /**
     * 
     * @param data
     */
    public Services(List<Datum__> data) {
        super();
        this.data = data;
    }

    public List<Datum__> getData() {
        return data;
    }

    public void setData(List<Datum__> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
