package com.mlsdev.weather.ui.model;

import com.mlsdev.weather.model.Weather;

/**
 * Created by romakukhar on 06.02.15.
 */
public class WeatherItem {

    private Weather weather;
    private boolean isEnabled;

    public WeatherItem(Weather weather, boolean isEnabled) {
        this.weather = weather;
        this.isEnabled = isEnabled;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
