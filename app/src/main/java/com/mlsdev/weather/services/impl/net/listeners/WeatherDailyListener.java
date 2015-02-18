package com.mlsdev.weather.services.impl.net.listeners;

import com.mlsdev.weather.model.DailyWeatherList;

/**
 * Created by romakukhar on 11.02.15.
 */
public interface WeatherDailyListener {

    public void onSuccessUpdateDailyWeather(DailyWeatherList dailyList);

    public void onErrorUpdateDailyWeather(String error);

    public void onSuccessFirstLoadDailyWeather(DailyWeatherList dailyList);

    public void onErrorFirstLoadDailyWeather(String error);
}
