package com.mlsdev.weather.services.impl.net.listeners;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.model.WeatherList;

/**
 * Created by romakukhar on 05.02.15.
 */
public interface BaseWeatherListener {

    void onSuccessGetWeatherByCity(Weather weather);

    void onErrorGetWeatherByCity(String error);

    void onSuccessGetAllWeathers(WeatherList weatherList);

    void onErrorGetAllWeathers(String error);
}
