package com.mlsdev.weather.dao;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.mlsdev.weather.model.Weather;

/**
 * Created by romakukhar on 13.02.15.
 */
public class WeatherDao {

    private RuntimeExceptionDao<Weather, Integer> weatherRuntimeDao = DaoManager.getWeatherRuntimeDao();

    public void createWeather(Weather weather) {
        weatherRuntimeDao.create(weather);
    }

    public void deleteWeather(Weather weather) {
        weatherRuntimeDao.queryRaw("delete from clouds where id = " + weather.getClouds().getId());
        weatherRuntimeDao.queryRaw("delete from coordinates where id = " + weather.getCoordinates().getId());
        weatherRuntimeDao.queryRaw("delete from sys where idDB = " + weather.getSys().getIdDB());
        weatherRuntimeDao.queryRaw("delete from temperature where id = " + weather.getTemperature().getId());
        weatherRuntimeDao.queryRaw("delete from wind where id = " + weather.getClouds().getId());
        weatherRuntimeDao.queryRaw("delete from clouds where id = " + weather.getClouds().getId());
    }

    public void deleteAllWeather() {

    }


}
