package com.example.e610.naghmaty.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.example.e610.naghmaty.Utils.Callbacks;

import java.util.ArrayList;

//public class LocalData extends AsyncTask<Void, Void, Object> implements Serializable , LoaderManager.LoaderCallbacks<Cursor>{
public class LocalData extends AsyncTask<Void, Void, Object> {

    Context context;
    String operationType;
    String companyCategoryTitle;
    String jsonContent;
    CompanyCategory companyCategory;
    public final static String insert="insert";
    private final static String update="update";
    public final static String select="select";


    public LocalData(Context ctx) {
    context=ctx;
    }

    public LocalData(Context context , String op ,CompanyCategory category) {
        this.context = context;
        companyCategory=category;
        operationType = op;
        companyCategoryTitle=companyCategory.getTitle();
        jsonContent=companyCategory.getJsonContent();

        /*if (operationType.equals(insert)) {

        } else if (operationType.equals(update)) {

        }else if(operationType.equals(select)){

        }*/

    }

    ArrayList<Callbacks> callBacks=new ArrayList<>();

    public void addCallBacks(Callbacks callBack) {
        this.callBacks.add(callBack);
    }

    private void notifyAllSubscribers(Object object) {
        for (Callbacks c: callBacks) {
            c.OnSuccess(object);
        }
    }

    @Override
    protected Object doInBackground(Void... params) {

        synchronized (context) {

            if (operationType.equals(insert)) {
                boolean b= saveInDatabase(companyCategory);
                return b;
            } else if (operationType.equals(update)) {
                return null;
            }else if(operationType.equals(select)){
                 CompanyCategory model= selectCategory(companyCategoryTitle);
                return model;
            }
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);

        if (operationType.equals(insert)) {
            boolean b=(boolean)object;
            notifyAllSubscribers(b);
        } else if (operationType.equals(update) ) {
            //boolean b=(boolean)object;
            //callBacks.modelDeleted(b);
        }
        else if (operationType.equals(select) ) {
            CompanyCategory model=(CompanyCategory)object;
            notifyAllSubscribers(model);
            //callBacks.favouriteChecked(b);
        }
    }

    private CompanyCategory selectCategory(String title) {
        CompanyCategory model=new CompanyCategory();
        Cursor  cursor = context.getContentResolver().query(
                CompanyCategoryContract.CompanyCategoryEntry.CONTENT_URI,
                new String[]{CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_title
                        ,CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_CONTENT},
                CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_title + " = ?" , new String[]{title},
                null,
                null);

          if (cursor != null && cursor.moveToFirst()) {
            model.setTitle(cursor.getString(0));
            model.setJsonContent(cursor.getString(1));
            cursor.close();
            return model;
        } else {
            return null;
        }
    }



    public boolean updateDatabase(String title, ContentValues companyCategoryValues) {
            try {
                context.getContentResolver().update(
                        CompanyCategoryContract.CompanyCategoryEntry.CONTENT_URI,companyCategoryValues,
                        CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_title + " = ?",  new String[]{title});
                return true;
            } catch (Exception ex) {
                return false;
            }

    }

    public boolean saveInDatabase(CompanyCategory model) {

        ContentValues companyCategoryValues = new ContentValues();
        companyCategoryValues.put(CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_ID,
                model.getId());
        companyCategoryValues.put(CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_title,
                model.getTitle());
        companyCategoryValues.put(CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_CONTENT,
                model.getJsonContent());
        if (selectCategory(model.getTitle())==null) {
            try {
                context.getContentResolver().insert(
                        CompanyCategoryContract.CompanyCategoryEntry.CONTENT_URI,
                        companyCategoryValues);
                return true;
            }
            catch (Exception ex){
                Log.d("tagasd","error",ex);
                return false;}
        }
        else {
            updateDatabase(model.getTitle(), companyCategoryValues);
            return false;
        }

    }


}
