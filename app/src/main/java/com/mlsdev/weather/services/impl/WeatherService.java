package com.mlsdev.weather.services.impl;

import com.mlsdev.weather.dao.DaoManager;
import com.mlsdev.weather.dao.WeatherDao;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.IWeatherService;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public class WeatherService implements IWeatherService {

    private WeatherDao weatherDao = DaoManager.getWeatherDao();

    @Override
    public void saveWeather(Weather weather) {
        weatherDao.createWeather(weather);
    }

    @Override
    public void createOrUpdateWeather(Weather weather) {

    }

    @Override
    public void deleteWeather(Weather weather) {
        weatherDao.deleteWeather(weather);
    }

    @Override
    public void deleteAllWeathers() {
        weatherDao.deleteAllWeather();
    }

    @Override
    public void deleteWeatherList(List<Weather> list) {
        for (Weather weather : list) {
            ServiceManager.getWeatherService().deleteWeather(weather);
        }
    }

    @Override
    public void updateWeathers(List<Weather> weathers) {
        for (Weather weather : weathers) {
            weatherDao.deleteWeather(weather);
            weatherDao.createWeather(weather);
        }
    }

    @Override
    public List<Weather> getAllWeathers() {
        return DaoManager.getWeatherRuntimeDao().queryForAll();
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
