package com.mlsdev.weather.services;

import com.mlsdev.weather.model.Weather;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public interface IWeatherService {

    public void createWeather(Weather weather);

    public void createOrUpdateWeather(Weather weather);

    public void deleteWeather(Weather weather);

    public void deleteAllWeather();

    public void deleteWeatherList(List<Weather> weathers);

    public void updateWeathers(List<Weather> weathers);

    public List<Weather> getAllWeathers();

    public Weather getWeatherByCityName(String cityName);

    public Weather getWeatherById(int id);

    public List<String> getCitiesId();

}
