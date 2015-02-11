package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by romakukhar on 11.02.15.
 */
public class DailyWeatherList {

    @SerializedName("list")
    private List<DayWeather> list;

    @SerializedName("cnt")
    private int count;

    public List<DayWeather> getList() {
        return list;
    }

    public void setList(List<DayWeather> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
