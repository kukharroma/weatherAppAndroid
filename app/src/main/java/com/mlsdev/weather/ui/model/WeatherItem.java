package com.mlsdev.weather.ui.model;

import com.mlsdev.weather.model.Weather;

/**
 * Created by romakukhar on 06.02.15.
 */
public class WeatherItem {

    private Weather weather;
    private boolean isEnabledDelete;

    public WeatherItem(Weather weather, boolean isEnabledDelete) {
        this.weather = weather;
        this.isEnabledDelete = isEnabledDelete;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public boolean isEnabledDelete() {
        return isEnabledDelete;
    }

    public void setEnabledDelete(boolean isEnabled) {
        this.isEnabledDelete = isEnabled;
    }
}
