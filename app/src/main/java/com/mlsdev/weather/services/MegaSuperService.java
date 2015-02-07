package com.mlsdev.weather.services;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.net.WeatherNetworkService;
import com.mlsdev.weather.services.impl.net.listeners.IGetWeatherByCity;

/**
 * Created by romakukhar on 07.02.15.
 */
public class MegaSuperService implements IGetWeatherByCity {

    private IGetWeatherByCity iGetWeatherByCity;

    public MegaSuperService(IGetWeatherByCity iGetWeatherByCity) {
        this.iGetWeatherByCity = iGetWeatherByCity;
    }

    private WeatherNetworkService networkService = new WeatherNetworkService(this);

    private void getWeather(String cityName) {
        networkService.getWeatherByCity(cityName);
    }

    @Override
    public void onSuccessGetWeatherByCity(Weather weather) {
        iGetWeatherByCity.onSuccessGetWeatherByCity(weather);
    }

    @Override
    public void onErrorGetWeatherByCity(String error) {
        iGetWeatherByCity.onErrorGetWeatherByCity(error);
    }
}
