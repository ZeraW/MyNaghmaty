package com.example.e610.naghmaty.Data;

/**
 * Created by Ahmed Fahmy on 12/26/2017.
 */

public class CompanyCategory {

    private String id;
    private String title;
    private String jsonContent;

    public CompanyCategory() {
    }

    public CompanyCategory(String id, String title, String jsonContent) {
        this.id = id;
        this.title = title;
        this.jsonContent = jsonContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }
}
