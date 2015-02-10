package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by romakukhar on 10.02.15.
 */
public class WeatherList {

    @SerializedName("cnt")
    private int count;

    @SerializedName("list")
    private List<Weather> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Weather> getList() {
        return list;
    }

    public void setList(List<Weather> list) {
        this.list = list;
    }
}
