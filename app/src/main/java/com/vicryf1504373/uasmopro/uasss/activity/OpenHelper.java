package com.vicryf1504373.uasmopro.uasss.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dbData.db";
    public static final String TABLE_CREATE =
            "CREATE TABLE DATA (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATA1 INTEGER, DATA2 INTEGER)";

    public OpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create DB
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //jika app diupgrade (diinstall yang baru) maka database akan dicreate ulang (data hilang)
        //jika tidak tidak ingin hilang, bisa diproses disini
        db.execSQL("DROP TABLE IF EXISTS DATA");
    }
}
