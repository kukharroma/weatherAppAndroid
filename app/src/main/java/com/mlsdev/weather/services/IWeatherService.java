package com.mlsdev.weather.services;

import com.mlsdev.weather.model.Weather;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public interface IWeatherService {

    public void saveWeather(Weather weather);

    public void createOrUpdateWeather(Weather weather);

    public void deleteWeather(Weather weather);

    public void deleteAllWeather(List<Weather> weathers);

    public List<Weather> getAllWeathers();

    public Weather getWeatherByCityName(String cityName);

    public Weather getWeatherById(int id);


}
