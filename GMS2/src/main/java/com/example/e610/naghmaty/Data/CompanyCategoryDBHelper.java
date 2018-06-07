/*
 * Copyright 2016.  Dmitry Malkovich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.e610.naghmaty.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CompanyCategoryDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CompanyCategory.db";

    public CompanyCategoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + CompanyCategoryContract.CompanyCategoryEntry.TABLE_NAME
                + " (" +
                CompanyCategoryContract.CompanyCategoryEntry._ID + " INTEGER PRIMARY KEY ," +
                CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_ID + " INTEGER NOT NULL, " +
                CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_title + " TEXT NOT NULL, " +
                CompanyCategoryContract.CompanyCategoryEntry.COLUMN_CompanyCategory_CONTENT + " TEXT NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CompanyCategoryContract.CompanyCategoryEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
