
package com.example.e610.naghmaty.Models.Clients;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum_> data = null;
    public final static Creator<Products> CREATOR = new Creator<Products>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        public Products[] newArray(int size) {
            return (new Products[size]);
        }

    }
    ;

    protected Products(Parcel in) {
        in.readList(this.data, (com.example.e610.naghmaty.Models.Clients.Datum_.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Products() {
    }

    /**
     * 
     * @param data
     */
    public Products(List<Datum_> data) {
        super();
        this.data = data;
    }

    public List<Datum_> getData() {
        return data;
    }

    public void setData(List<Datum_> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
