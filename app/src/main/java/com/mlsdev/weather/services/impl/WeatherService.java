package com.mlsdev.weather.services.impl;

import com.mlsdev.weather.dao.DaoManager;
import com.mlsdev.weather.dao.WeatherDao;
import com.mlsdev.weather.model.DayWeather;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.IWeatherService;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public class WeatherService implements IWeatherService {

    private WeatherDao weatherDao = DaoManager.getWeatherDao();

    @Override
    public void createWeather(Weather weather) {
        weatherDao.createWeather(weather);
    }

    public void updateWeather(Weather weather) {
        weatherDao.updateWeather(weather);
    }

    @Override
    public void createOrUpdateWeather(Weather weather) {
        weatherDao.createOrUpdateWeather(weather);
    }

    @Override
    public void updateWeathers(List<Weather> weathers) {
        weatherDao.updateWeathers(weathers);
    }

    @Override
    public void createOrUpdateDailyWeather(Weather weather, List<DayWeather> list) {
        weatherDao.createOrUpdateDailyWeather(weather, list);
    }

    @Override
    public void createDailyWeather(Weather weather, List<DayWeather> list) {
        weatherDao.createDailyWeather(weather, list);
    }

    @Override
    public void updateDailyWeather(Weather weather, List<DayWeather> list) {
        weatherDao.updateDailyWeather(weather, list);
    }

    @Override
    public void deleteDailyWeather(Weather weather) {
        weatherDao.deleteDailyWeather(weather);
    }

    @Override
    public void deleteWeather(Weather weather) {
        weatherDao.deleteWeather(weather);
    }

    @Override
    public void deleteAllWeather() {
        weatherDao.deleteAllWeather();
    }

    @Override
    public void deleteWeatherList(List<Weather> list) {
        for (Weather weather : list) {
            weatherDao.deleteWeather(weather);
        }
    }

    @Override
    public List<Weather> getAllWeathers() {
        return weatherDao.getAllWeathers();
    }

    @Override
    public Weather getWeatherById(int id) {
        return weatherDao.getWeatherById(id);
    }

    @Override
    public Weather getWeatherByCityName(String cityName) {
        return weatherDao.getWeatherByCityName(cityName);
    }

    @Override
    public List<String> getCitiesId() {
        return weatherDao.getCitiesId();
    }
}
