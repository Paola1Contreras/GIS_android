package edu.tecii.android.proyectoportalaiesec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paola on 02/12/2016.
 */

public class DB extends SQLiteOpenHelper {

    private  static final String DB_NAME= "json";
    private static final int SCHEME_VERSION=1;
    private SQLiteDatabase db;

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContent.FIELD_CREATE_DB_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
