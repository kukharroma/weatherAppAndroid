package com.mlsdev.weather.services.impl.net.listeners;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.model.WeatherList;

/**
 * Created by romakukhar on 05.02.15.
 */
public interface BaseWeatherListener {

    public void onSuccessGetWeatherByCity(Weather weather);

    public void onErrorGetWeatherByCity(String error);

    public void onSuccessGetAllWeathers(WeatherList weatherList);

    public void onErrorGetAllWeathers(String error);
}
