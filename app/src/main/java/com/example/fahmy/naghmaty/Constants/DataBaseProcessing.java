package com.example.fahmy.naghmaty.Constants;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.e610.naghmaty.Data.CompanyCategory;
import com.example.e610.naghmaty.Data.LocalData;
import com.example.e610.naghmaty.Utils.Callbacks;


/**
 * Created by Ahmed Fahmy on 12/27/2017.
 */

public class DataBaseProcessing {
    CompanyCategory model2;
    Context context;
    Callbacks callbacks;


    public DataBaseProcessing(Context context) {
        this.context = context;

    }

    public void insertToDataBase(String id, String title, String response) {
        CompanyCategory companyCategory = new CompanyCategory(id, title, response);
        LocalData localData1 = new LocalData(context, LocalData.insert, companyCategory);
        localData1.addCallBacks(new Callbacks() {
            @Override
            public void OnSuccess(Object object) {
                try {
                    boolean b = (boolean) object;
                    if (b) {
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void OnFailure(String errorMsg) {

            }
        });
        localData1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void selectFromDataBase(final Context context1, String id, String title) {
        CompanyCategory companyCategory = new CompanyCategory(id, title, "");
        LocalData localData1 = new LocalData(context1, LocalData.select, companyCategory);
        localData1.addCallBacks(new Callbacks() {

            @Override
            public void OnSuccess(Object object) {
                if (object== null || object.equals("")) {
                    Toast.makeText(context1, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_SHORT).show();

                }
                Log.e("Model=",""+object);

                try {
                    CompanyCategory model = (CompanyCategory) object;

                    if (model != null) {
                        callbacks.OnSuccess(model);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void OnFailure(String errorMsg) {

            }
        });
        localData1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void setCallbacks(Callbacks callback) {
        this.callbacks = callback;

    }


}
