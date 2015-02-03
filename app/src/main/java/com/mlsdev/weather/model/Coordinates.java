package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by romakukhar on 30.01.15.
 */
@DatabaseTable(tableName = "coordinates")
public class Coordinates {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "lon")
    @SerializedName("lon")
    private double lon;

    @DatabaseField(columnName = "lat")
    @SerializedName("lat")
    private double lat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
