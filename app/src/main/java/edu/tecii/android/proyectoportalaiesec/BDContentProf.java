package edu.tecii.android.proyectoportalaiesec;

import java.net.URL;

/**
 * Created by Paola on 06/12/2016.
 */

//NO MOVERLE A ESTA OTRA VAINA PLZ SEÑOR DIRECTOR, NO QUIERO REPROBAR
public class BDContentProf  {
    public static final String TABLE_NAME="profiles";
    public static final String FIELD_ID="id";
    public static final String FIELD_SHORT_NAME="short_name";
    public static final String FIELD_COUSTUMER_NAME="coustumer_name";
    public static final String FIELD_DESCIPTION="description";
    public static final String FIELD_COLOR="color";
    public static final String FIELD_GROUP_ID="group_id";
    public static final String FIELD_PROFILES_PHOTO_URLS="profiles_photo_urls";
    public static final String FIELD_COVER_PHOTO_URLS="cover_photo_urls";

    public static final String FIELD_CREATE_DB_TABLE="create table"+ TABLE_NAME +"{"+
            FIELD_ID+ "integer primary key autoincrement,"+
            FIELD_SHORT_NAME+"text"+
            FIELD_COUSTUMER_NAME+"text"+
            FIELD_DESCIPTION+"text"+
            FIELD_COLOR+"text"+
            FIELD_GROUP_ID+"integer autoincrement"+
            FIELD_PROFILES_PHOTO_URLS+"text"+
            FIELD_COVER_PHOTO_URLS+"text"+
                       "}";
    private int id;
    private String short_name;
    private String coustumer_name;
    private String description;
    private String color;
    private int group_id;

    private String profiles_photo_urls;
    private String cover_photo_urls;


    public BDContentProf(int id, String short_name,
                     String coustumer_name, String description, String color,
                     int group_id, String profiles_photo_urls,
                         String cover_photo_urls) {
        super();

        this.id= id;
        this.short_name = short_name;
        this.coustumer_name = coustumer_name;
        this.description = description;
        this.color=color;
        this.group_id=group_id;
        this.profiles_photo_urls=profiles_photo_urls;
        this.cover_photo_urls=cover_photo_urls;


    }


}


