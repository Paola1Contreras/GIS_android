package edu.tecii.android.proyectoportalaiesec;

import java.net.URL;

/**
 * Created by Paola on 02/12/2016.
 */

public class DBContent {
    public static final String TABLE_NAME="opportunities";
    public static final String FIELD_ID="id";
    public static final String FIELD_TITLE="title";
    public static final String FIELD_URL="url";
    public static final String FIELD_STATUS="status";
    public static final String FIELD_LOCATION="location";
    public static final String FIELD_PROGRAMMES="programmes";
    public static final String FIELD_SHORT_NAME="short_name";
    public static final String FIELD_APLICATIONS_COUNT="applications";
    public static final String FIELD_IS_FAVORITED="is_favorited";
    public static final String FIELD_ORGANISATION_ID="organization_id";
    public static final String FIELD_CREATE_DB_TABLE="create table"+ TABLE_NAME +"{"+
            FIELD_ID+ "integer primary key autoincrement,"+
            FIELD_TITLE+"text"+
            FIELD_URL+"url"+
            FIELD_STATUS+"text"+
            FIELD_LOCATION+"text"+
            FIELD_PROGRAMMES+"text"+
            FIELD_SHORT_NAME+"text"+
            FIELD_APLICATIONS_COUNT+"integer autoincrement"+
            FIELD_IS_FAVORITED+"text"+
            FIELD_ORGANISATION_ID+"integer autoincrement"+
            "}";
    private int id;
    private String title;
    private URL url;
    private String status;
    private String location;
    private String programmes;
    private String short_name;
    private int aplications_count;
    private String is_favorited;
    private int organisation_id;

    public DBContent(String title, URL url, String status,
                     String location, String programmes, String short_name,
                     int aplications_count, String is_favorited,
                     int organisation_id, int id) {
        this.title = title;
        this.url = url;
        this.status = status;
        this.location = location;
        this.programmes = programmes;
        this.short_name = short_name;
        this.aplications_count = aplications_count;
        this.is_favorited = is_favorited;
        this.organisation_id = organisation_id;
        this.id = id;

      
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProgrammes() {
        return programmes;
    }

    public void setProgrammes(String programmes) {
        this.programmes = programmes;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public int getAplications_count() {
        return aplications_count;
    }

    public void setAplications_count(int aplications_count) {
        this.aplications_count = aplications_count;
    }

    public String getIs_favorited() {
        return is_favorited;
    }

    public void setIs_favorited(String is_favorited) {
        this.is_favorited = is_favorited;
    }

    public int getOrganisation_id() {
        return organisation_id;
    }

    public void setOrganisation_id(int organisation_id) {
        this.organisation_id = organisation_id;
    }
}
