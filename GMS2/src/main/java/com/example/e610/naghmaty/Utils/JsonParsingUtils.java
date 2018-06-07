package com.example.e610.naghmaty.Utils;

import com.example.e610.naghmaty.Models.CategoriesResponse.CategoriesResponse;
import com.example.e610.naghmaty.Models.Clients.Clients;
import com.example.e610.naghmaty.Models.Gallery.Gallery;
import com.example.e610.naghmaty.Models.Products.Products;
import com.example.e610.naghmaty.Models.Projects.Projects;
import com.example.e610.naghmaty.Models.Services.Services;
import com.example.e610.naghmaty.Models.SubDirectory.SubDirectory;
import com.google.gson.Gson;

/**
 * Created by Ahmed Fahmy on 1/28/2018.
 */

public  class JsonParsingUtils {

    public static String getJson(Object obj){
        String jsonStr="";
        Gson gson=new Gson();
        jsonStr=gson.toJson(obj);
        return jsonStr;
    }

    public static Object getObject(String jsonSr,Class classType){
        Gson gson=new Gson();
        if (classType==SubDirectory.class) {
            SubDirectory subDirectory = new SubDirectory();
            subDirectory = gson.fromJson(jsonSr, SubDirectory.class);
            return subDirectory;
        }else if (classType==Clients.class) {
            Clients clients = new Clients();
            clients = gson.fromJson(jsonSr, Clients.class);
            return clients;
        }else if (classType==Gallery.class) {
            Gallery gallery = new Gallery();
            gallery = gson.fromJson(jsonSr, Gallery.class);
            return gallery;
        }else if (classType==Products.class) {
            Products products = new Products();
            products = gson.fromJson(jsonSr, Products.class);
            return products;
        }else if (classType==Projects.class) {
            Projects  projects = new Projects();
            projects = gson.fromJson(jsonSr, Projects.class);
            return projects;
        }else if (classType==Services.class) {
            Services  services = new Services();
            services = gson.fromJson(jsonSr, Services.class);
            return services;
        }else if (classType==CategoriesResponse.class) {
            CategoriesResponse  services = new CategoriesResponse();
            services = gson.fromJson(jsonSr, CategoriesResponse.class);
            return services;
        }else
            return null;
    }
}
