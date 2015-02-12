package com.mlsdev.weather.services.impl;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.mlsdev.weather.dao.DaoManager;
import com.mlsdev.weather.model.DayWeather;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.IWeatherService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public class WeatherService implements IWeatherService {


    @Override
    public void saveWeather(Weather weather) {
        DaoManager.getWeatherRuntimeDao().create(weather);
    }

    @Override
    public void createOrUpdateWeather(Weather weather) {
        DaoManager.getWeatherRuntimeDao().createOrUpdate(weather);
    }

    @Override
    public void deleteWeather(Weather weather) {
        DaoManager.getWeatherRuntimeDao().delete(weather);
    }

    @Override
    public void deleteAllWeathers() {
        DaoManager.getWeatherRuntimeDao().queryRaw("delete from weather ; " +
                "delete from sys ;" +
                "delete from coordinates ; " +
                "delete from temperature;" +
                "delete from wind; " +
                "delete from clouds;" +
                "delete from description; " +
                "delete from detailDayTemp; " +
                "delete from dayTemp; " +
                "delete from rain; " +
                "delete from snow;");
    }

    @Override
    public void deleteWeatherList(List<Weather> list) {
        DaoManager.getWeatherRuntimeDao().delete(list);
        for (Weather weather : list) {
            // todo deleting all tables
            DaoManager.getWeatherRuntimeDao().queryRaw("delete from sys where idDB = " + weather.getSys().getIdDB() + ";" +
                    "delete from coordinates where id = " + weather.getCoordinates().getId() + " ; " +
                    "delete from temperature where id = " + weather.getTemperature().getId() + " ; " +
                    "delete from wind where id = " + weather.getWind().getId() + " ; " +
                    "delete from clouds where id = " + weather.getClouds().getId() + " ; " +
                    "delete from description where id = " + weather.getFirstWeather().getId());
            for (DayWeather dayWeather : weather.getDayTempList()) {
                DaoManager.getWeatherRuntimeDao().queryRaw("delete from detailDayTemp where id = " + dayWeather.getDetailDayWeatherTemp().getId());
            }
            DaoManager.getWeatherRuntimeDao().queryRaw("delete from dayTemp where weather_id = " + weather.getId());
        }
    }

    @Override
    public void updateWeathers(List<Weather> weathers) {
        for (Weather weather : weathers) {
            DaoManager.getWeatherRuntimeDao().createOrUpdate(weather);
        }
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

    @Override
    public List<String> getCitiesId() {
        List<String> resultList = new ArrayList<>();
        try {
            QueryBuilder<Weather, Integer> queryBuilder = DaoManager.getWeatherRuntimeDao().queryBuilder();
            queryBuilder.selectColumns("id");
            GenericRawResults<String[]> rawResults = DaoManager.getWeatherRuntimeDao().queryRaw(queryBuilder.prepareStatementString());
            for (String[] IdItem : rawResults) {
                resultList.add(IdItem[0]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
