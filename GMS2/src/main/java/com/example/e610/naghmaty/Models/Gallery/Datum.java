
package com.example.e610.naghmaty.Models.Gallery;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ar_title")
    @Expose
    private String arTitle;
    @SerializedName("en_title")
    @Expose
    private String enTitle;
    @SerializedName("ar_description")
    @Expose
    private String arDescription;
    @SerializedName("en_description")
    @Expose
    private String enDescription;
    @SerializedName("image")
    @Expose
    private String image;
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
        this.arTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.enTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.arDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.enDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param enTitle
     * @param arTitle
     * @param id
     * @param enDescription
     * @param image
     * @param arDescription
     */
    public Datum(Integer id, String arTitle, String enTitle, String arDescription, String enDescription, String image) {
        super();
        this.id = id;
        this.arTitle = arTitle;
        this.enTitle = enTitle;
        this.arDescription = arDescription;
        this.enDescription = enDescription;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArTitle() {
        return arTitle;
    }

    public void setArTitle(String arTitle) {
        this.arTitle = arTitle;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(arTitle);
        dest.writeValue(enTitle);
        dest.writeValue(arDescription);
        dest.writeValue(enDescription);
        dest.writeValue(image);
    }

    public int describeContents() {
        return  0;
    }

}
