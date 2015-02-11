package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by romakukhar on 02.02.15.
 */
@DatabaseTable(tableName = "detailDayTemp")
public class DetailDayWeatherTemp {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "day")
    @SerializedName("day")
    private double day;

    @DatabaseField(columnName = "min")
    @SerializedName("min")
    private double min;

    @DatabaseField(columnName = "max")
    @SerializedName("max")
    private double max;

    @DatabaseField(columnName = "night")
    @SerializedName("night")
    private double night;

    @DatabaseField(columnName = "eve")
    @SerializedName("eve")
    private double eve;

    @DatabaseField(columnName = "morn")
    @SerializedName("morn")
    private double morn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getNight() {
        return night;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getEve() {
        return eve;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public double getMorn() {
        return morn;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }
}
