package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by romakukhar on 30.01.15.
 */
@DatabaseTable(tableName = "temperature")
public class Temperature {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "temp")
    @SerializedName("temp")
    private String temp;

    @DatabaseField(columnName = "humidity")
    @SerializedName("humidity")
    private int humidity;

    @DatabaseField(columnName = "minTemp")
    @SerializedName("temp_min")
    private int minTemp;

    @DatabaseField(columnName = "maxTemp")
    @SerializedName("temp_max")
    private int maxTemp;

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
