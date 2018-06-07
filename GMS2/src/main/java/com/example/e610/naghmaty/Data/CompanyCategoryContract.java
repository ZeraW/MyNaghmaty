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

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class CompanyCategoryContract {

    public static final String CONTENT_AUTHORITY = "Company.Category2";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CompanyCategory = "CompanyCategory";

    public static final class CompanyCategoryEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CompanyCategory).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CompanyCategory;

        public static final String TABLE_NAME = "CompanyCategory";
        public static final String COLUMN_CompanyCategory_ID = "CompanyCategory_id";
        public static final String COLUMN_CompanyCategory_title = "CompanyCategory_title";
        public static final String COLUMN_CompanyCategory_CONTENT = "CompanyCategory_content";



        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String[] Series_COLUMNS = {
                COLUMN_CompanyCategory_ID,
                COLUMN_CompanyCategory_title,
                COLUMN_CompanyCategory_CONTENT,
        };

        public static final int COL_CompanyCategory_ID = 1;
        public static final int COL_CompanyCategory_title = 2;
        public static final int COL_CompanyCategory_CONTENT = 3;
    }
}
