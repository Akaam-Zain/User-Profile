package com.example.simpleapp.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Users.db";

        public DbHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                        UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                        UserProfile.Users.COLUMN_1 + " TEXT," +
                        UserProfile.Users.COLUMN_2 + " TEXT," +
                        UserProfile.Users.COLUMN_3 + " TEXT," +
                        UserProfile.Users.COLUMN_4 + " TEXT)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;

        public long addInfo(String username, String password, String dob, String gender)
        {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(UserProfile.Users.COLUMN_1, username);
            values.put(UserProfile.Users.COLUMN_2, dob);
            values.put(UserProfile.Users.COLUMN_3, password);
            values.put(UserProfile.Users.COLUMN_2, gender);


            long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);
            return newRowId;
        }

        public boolean updateInfo(String username, String password, String dob, String gender) {
            SQLiteDatabase db = getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(UserProfile.Users.COLUMN_2, dob);
            values.put(UserProfile.Users.COLUMN_3, password);
            values.put(UserProfile.Users.COLUMN_4, gender);

            String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
            String[] selectionArgs = {username};
            int count = db.update(
                    UserProfile.Users.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

            if(count > 0)
                return true;

            else
                return false;
        }

        public List readAllInfo()
        {
            SQLiteDatabase db = getReadableDatabase();
            String username = "Akaam";

            String[] projection = {
                    BaseColumns._ID,
                    UserProfile.Users.COLUMN_1,
                    UserProfile.Users.COLUMN_2,
                    UserProfile.Users.COLUMN_3,
                    UserProfile.Users.COLUMN_4,
            };

            String selection = UserProfile.Users.COLUMN_1+ " = ?";
            String[] selectionArgs = { username };


            String sortOrder =
                        UserProfile.Users.COLUMN_1 + " ASC";

            Cursor cursor = db.query(
                    UserProfile.Users.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder
            );

            List usernames = new ArrayList<>();
            while(cursor.moveToNext()) {
               String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
                usernames.add(user);
            }
            cursor.close();

            return usernames;
        }


    public List readAllInfo(String username)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_2,
                UserProfile.Users.COLUMN_3,
                UserProfile.Users.COLUMN_4,
        };

        String selection = UserProfile.Users.COLUMN_1+ " LIKE ?";
        String[] selectionArgs = { username };


        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List userInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_2));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_3));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_4));
            userInfo.add(user);

        }
        cursor.close();

        return userInfo;
    }



        public void deleteInfo(String username)
        {
            SQLiteDatabase db = getWritableDatabase();
            String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
            String[] selectionArgs = { username };
            int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);

        }


    }


