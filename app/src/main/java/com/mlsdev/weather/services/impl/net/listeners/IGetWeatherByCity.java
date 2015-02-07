package com.mlsdev.weather.services.impl.net.listeners;

import com.mlsdev.weather.model.Weather;

/**
 * Created by romakukhar on 05.02.15.
 */
public interface IGetWeatherByCity {

    public void onSuccessGetWeatherByCity(Weather weather);

    public void onErrorGetWeatherByCity(String error);
}
