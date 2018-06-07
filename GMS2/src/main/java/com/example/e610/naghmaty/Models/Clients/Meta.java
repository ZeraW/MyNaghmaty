
package com.example.e610.naghmaty.Models.Clients;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta implements Parcelable
{

    @SerializedName("ar_tagline")
    @Expose
    private String arTagline;
    @SerializedName("en_tagline")
    @Expose
    private String enTagline;
    public final static Creator<Meta> CREATOR = new Creator<Meta>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Meta createFromParcel(Parcel in) {
            return new Meta(in);
        }

        public Meta[] newArray(int size) {
            return (new Meta[size]);
        }

    }
    ;

    protected Meta(Parcel in) {
        this.arTagline = ((String) in.readValue((String.class.getClassLoader())));
        this.enTagline = ((String) in.readValue((String.class.getClassLoader())));
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(arTagline);
        dest.writeValue(enTagline);
    }

    public int describeContents() {
        return  0;
    }

}
