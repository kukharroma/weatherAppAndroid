package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by romakukhar on 02.02.15.
 */
@DatabaseTable(tableName = "dayTemp")
public class DayWeather {

    @DatabaseField(columnName = "weather_id", foreign = true)
    @SerializedName("o")
    private Weather weather;

    @DatabaseField(generatedId = true)
    private int id;

    @SerializedName("temp")
    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, maxForeignAutoRefreshLevel = 1)
    private DetailDayWeatherTemp detailDayWeatherTemp;

    @DatabaseField(columnName = "date")
    @SerializedName("dt")
    private String date;

    @DatabaseField(columnName = "speed")
    @SerializedName("speed")
    private double speed;

    @DatabaseField(columnName = "clouds")
    @SerializedName("clouds")
    private double clouds;

    @DatabaseField(columnName = "rain")
    @SerializedName("rain")
    private double rain;

    @DatabaseField(columnName = "snow")
    @SerializedName("snow")
    private double snow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DetailDayWeatherTemp getDetailDayWeatherTemp() {
        return detailDayWeatherTemp;
    }

    public void setDetailDayWeatherTemp(DetailDayWeatherTemp detailDayWeatherTemp) {
        this.detailDayWeatherTemp = detailDayWeatherTemp;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getSnow() {
        return snow;
    }

    public void setSnow(double snow) {
        this.snow = snow;
    }
}
