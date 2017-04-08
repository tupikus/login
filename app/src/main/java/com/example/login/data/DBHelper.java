package com.example.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 101 on 08.04.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Polzovateli";
    public static final String TABLE_INFO = "INFO";

    public static final String KEY_ID = "_id";
    public static final String KEY_USERNAME = "UserName";
    public static final String KEY_PASSWORD = "Password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_INFO + "(" + KEY_ID + " integer primary key,"
                + KEY_PASSWORD + " text,"
                + KEY_USERNAME + " text" + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_INFO);

    }
}
