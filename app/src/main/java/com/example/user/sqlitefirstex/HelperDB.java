package com.example.user.sqlitefirstex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.user.sqlitefirstex.Age.CLASS;
import static com.example.user.sqlitefirstex.Age.KEY_ID;
import static com.example.user.sqlitefirstex.Age.SHIHVA;
import static com.example.user.sqlitefirstex.Age.TABLE_AGE;
import static com.example.user.sqlitefirstex.Age.TYPE;
import static com.example.user.sqlitefirstex.Name.NAME;
import static com.example.user.sqlitefirstex.Name.ST_ID;
import static com.example.user.sqlitefirstex.Name.TABLE_NAME;

public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbexam.db";
    private static int DATABASE_VERSION = 1;
    String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.strCreate=strCreate;
        this.strDelete=strDelete;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate="CREATE TABLE "+TABLE_AGE;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+TYPE+" TEXT,";
        strCreate+=" "+CLASS+" TEXT,";
        strCreate+=" "+SHIHVA+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_NAME;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+ST_ID+" TEXT,";
        strCreate+=");";
        db.execSQL(strCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        strDelete="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_AGE;
        db.execSQL(strDelete);
        onCreate(db);
    }
}
