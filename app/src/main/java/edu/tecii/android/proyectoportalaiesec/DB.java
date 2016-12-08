package edu.tecii.android.proyectoportalaiesec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paola on 02/12/2016.
 */

public class DB extends SQLiteOpenHelper {

    private  static final String DB_NAME= "json";
    private static final int SCHEME_VERSION=2;
    private SQLiteDatabase db;

    public DB(Context context) {

        super(context, DB_NAME, null, SCHEME_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE profile(id INTEGER PRIMARY KEY AUTOINCREMENT, short_name TEXT NOT NULL, consumer_name TEXT NOT NULL"+
                "description TEXT NOT NULL, group_id INTEGER NOT NULL REFERENCES, profile_photo_urls TEXT NOT NULL"+
        "cover_photo_urls TEXT NOT NULL ");

        // TABLA CARRITO_DETALLE
        db.execSQL("CREATE TABLE Opp(opportunity_id INTEGER PRIMARY KEY AUTOINCREMENT, status TEXT NOT NULL, url URL, location TEXT NOT NULL" +
                "programmes TEXT NOT NULL, short_name TEXT NOT NULL, aplications_count INTEGER NOT NULL REFERENCES, is_favorited TEXT NOT NULL" +
                "organisation_id INTEGER NOT NULL REFERENCES");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
