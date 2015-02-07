package com.mlsdev.weather.services.impl.net.listeners;

import com.mlsdev.weather.model.Weather;

import java.util.List;

/**
 * Created by romakukhar on 07.02.15.
 */
public interface IGetAllWeatherById {

    public void onSuccessGetAllWeathers(List<Weather> weathers);

    public void onErrorGetAllWeathers(String error);
}
