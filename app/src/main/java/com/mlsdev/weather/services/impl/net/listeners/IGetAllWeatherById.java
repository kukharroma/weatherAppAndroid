package com.mlsdev.weather.services.impl.net.listeners;

import com.mlsdev.weather.model.WeatherList;

/**
 * Created by romakukhar on 07.02.15.
 */
public interface IGetAllWeatherById {

    public void onSuccessGetAllWeathers(WeatherList weatherList);

    public void onErrorGetAllWeathers(String error);
}
