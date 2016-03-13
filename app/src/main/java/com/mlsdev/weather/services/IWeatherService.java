package com.mlsdev.weather.services;

import com.mlsdev.weather.model.DayWeather;
import com.mlsdev.weather.model.Weather;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public interface IWeatherService {

    void createWeather(Weather weather);

    void createOrUpdateWeather(Weather weather);

    void deleteWeather(Weather weather);

    void createOrUpdateDailyWeather(Weather weather, List<DayWeather> list);

    void createDailyWeather(Weather weather, List<DayWeather> list);

    void updateDailyWeather(Weather weather, List<DayWeather> list);

    void deleteDailyWeather(Weather weather);

    void deleteAllWeather();

    void deleteWeatherList(List<Weather> weathers);

    void updateWeathers(List<Weather> weathers);

    List<Weather> getAllWeathers();

    Weather getWeatherByCityName(String cityName);

    Weather getWeatherById(int id);

    List<String> getCitiesId();

    List<String> getCitiesNames();

}
