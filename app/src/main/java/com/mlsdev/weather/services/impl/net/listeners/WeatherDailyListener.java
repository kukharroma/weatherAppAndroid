package com.mlsdev.weather.services.impl.net.listeners;

import com.mlsdev.weather.model.DailyWeatherList;

/**
 * Created by romakukhar on 11.02.15.
 */
public interface WeatherDailyListener {

    public void onSuccessGetDailyWeather(DailyWeatherList dailyList);

    public void onErrorGetDailyWeather(String error);

}
