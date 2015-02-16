package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by romakukhar on 30.01.15.
 */

@DatabaseTable(tableName = "weather")
public class Weather {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM hh:mm");

    public Weather() {
    }

    @DatabaseField(id = true)
    @SerializedName("id")
    private int id;

    @DatabaseField(columnName = "city")
    @SerializedName("name")
    private String city;

    @DatabaseField(columnName = "weatherTime")
    @SerializedName("dt")
    private String weatherTime;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1, columnDefinition = "integer references coordinates(id) on delete cascade")
    @SerializedName("coord")
    private Coordinates coordinates;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1, columnDefinition = "integer references sys(idDB) on delete cascade")
    @SerializedName("sys")
    private Sys sys;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1, columnDefinition = "integer references temperature(id) on delete cascade")
    @SerializedName("main")
    private Temperature temperature;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1, columnDefinition = "integer references wind(id) on delete cascade")
    @SerializedName("wind")
    private Wind wind;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1, columnDefinition = "integer references clouds(id) on delete cascade")
    @SerializedName("clouds")
    private Clouds clouds;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    @SerializedName("weather")
    private Description weather[];

    @ForeignCollectionField(eager = true, maxEagerForeignCollectionLevel = 2)
    private ForeignCollection<DayWeather> dayTempList;

    public ForeignCollection<DayWeather> getDayTempList() {
        return dayTempList;
    }

    public void setDayTempList(ForeignCollection<DayWeather> dayTempList) {
        this.dayTempList = dayTempList;
    }

    public Description getFirstWeather() {
        return weather[0];
    }

    public Description[] getWeather() {
        return weather;
    }

    public void setWeather(Description[] weather) {
        this.weather = weather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherTime(String weatherTime) {
        this.weatherTime = weatherTime;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getFormattedDate() {
        return dateFormat.format(new Date(Long.parseLong(this.weatherTime) * 1000));
    }
}
