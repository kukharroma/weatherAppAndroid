package com.mlsdev.weather.services.impl;

import android.util.Log;

import com.j256.ormlite.stmt.QueryBuilder;
import com.mlsdev.weather.dao.DaoManager;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.IWeatherService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public class WeatherService implements IWeatherService {


    @Override
    public void saveWeather(Weather weather) {
        DaoManager.getWeatherRuntimeDao().create(weather);
        Log.d(this.getClass().toString(), "Weather created");
    }

    @Override
    public void updateWeather(Weather weather) {
        DaoManager.getWeatherRuntimeDao().createOrUpdate(weather);
    }

    @Override
    public void deleteWeather(Weather weather) {
        DaoManager.getWeatherRuntimeDao().delete(weather);
    }

    @Override
    public void deleteAllWeather() {
//   todo      DaoManager.getWeatherRuntimeDao().deleteAll();
    }

    @Override
    public List<Weather> getAllWeathers() {
        return DaoManager.getWeatherRuntimeDao().queryForAll();
    }

    @Override
    public Weather getWeatherByCityName(String cityName) {
        Weather weather = null;
        try {
            QueryBuilder<Weather, Integer> queryBuilder = DaoManager.getWeatherRuntimeDao().queryBuilder();
            queryBuilder.where().eq("city", cityName);
            weather = (Weather) DaoManager.getWeatherRuntimeDao().queryRaw(queryBuilder.prepareStatementString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weather;
    }

    @Override
    public Weather getWeatherById(int id) {
        Weather weather = null;
        try {
            QueryBuilder<Weather, Integer> queryBuilder = DaoManager.getWeatherRuntimeDao().queryBuilder();
            queryBuilder.where().eq("id", id);
            weather = (Weather) DaoManager.getWeatherRuntimeDao().queryRaw(queryBuilder.prepareStatementString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
