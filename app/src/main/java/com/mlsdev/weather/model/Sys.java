package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by romakukhar on 30.01.15.
 */

@DatabaseTable(tableName = "sys")
public class Sys {

    @DatabaseField(id = true)
    @SerializedName("id")
    private int id;

    @DatabaseField(columnName = "country")
    @SerializedName("country")
    private String country;

    @DatabaseField(columnName = "sunrise")
    @SerializedName("sunrise")
    private String sunrise;

    @DatabaseField(columnName = "sunset")
    @SerializedName("sunset")
    private String sunset;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
