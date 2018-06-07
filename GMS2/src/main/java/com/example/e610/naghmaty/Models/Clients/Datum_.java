
package com.example.e610.naghmaty.Models.Clients;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_ implements Parcelable
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
    @SerializedName("images")
    @Expose
    private List<Object> images = null;
    public final static Creator<Datum_> CREATOR = new Creator<Datum_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum_ createFromParcel(Parcel in) {
            return new Datum_(in);
        }

        public Datum_[] newArray(int size) {
            return (new Datum_[size]);
        }

    }
    ;

    protected Datum_(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.arName = ((String) in.readValue((String.class.getClassLoader())));
        this.enName = ((String) in.readValue((String.class.getClassLoader())));
        this.arDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.enDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.logo = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.images, (Object.class.getClassLoader()));
    }

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
     * @param enDescription
     * @param enName
     * @param arName
     * @param images
     * @param arDescription
     */
    public Datum_(Integer id, String arName, String enName, String arDescription, String enDescription, String logo, List<Object> images) {
        super();
        this.id = id;
        this.arName = arName;
        this.enName = enName;
        this.arDescription = arDescription;
        this.enDescription = enDescription;
        this.logo = logo;
        this.images = images;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(arName);
        dest.writeValue(enName);
        dest.writeValue(arDescription);
        dest.writeValue(enDescription);
        dest.writeValue(logo);
        dest.writeList(images);
    }

    public int describeContents() {
        return  0;
    }

}
