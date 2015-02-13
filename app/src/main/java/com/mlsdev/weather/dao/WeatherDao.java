package com.mlsdev.weather.dao;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.mlsdev.weather.model.DayWeather;
import com.mlsdev.weather.model.Weather;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romakukhar on 13.02.15.
 */
public class WeatherDao {

    private RuntimeExceptionDao<Weather, Integer> weatherRuntimeDao = DaoManager.getWeatherRuntimeDao();

    public void createWeather(Weather weather) {
        weatherRuntimeDao.create(weather);
    }

    public void updateWeather(Weather weather) {
        Weather oldWeather = getWeatherById(weather.getId());
        weather.getCoordinates().setId(oldWeather.getCoordinates().getId());
        weather.getSys().setId(oldWeather.getSys().getId());
        weather.getTemperature().setId(oldWeather.getTemperature().getId());
        weather.getWind().setId(oldWeather.getWind().getId());
        weather.getClouds().setId(oldWeather.getClouds().getId());
        weather.getFirstWeather().setId(oldWeather.getFirstWeather().getId());
        for (DayWeather dayWeather : weather.getDayTempList()) {
            weatherRuntimeDao.queryRaw("delete from detailDayTemp where id = " + dayWeather.getId());
        }
    }

    public void updateDailyWeather(List<DayWeather> list){

    }

    public void createOrUpdateWeather(Weather weather) {

    }

    public void updateWeathers(List<Weather> weathers) {

    }

    public void deleteWeather(Weather weather) {
        // todo fix deleting dayTemp and detailDayTemp and other id's
        weatherRuntimeDao.queryRaw("delete from clouds where id = " + weather.getClouds().getId());
        weatherRuntimeDao.queryRaw("delete from coordinates where id = " + weather.getCoordinates().getId());
        weatherRuntimeDao.queryRaw("delete from dayTemp where weather_id = " + weather.getId());

        for (DayWeather dayWeather : weather.getDayTempList()) {
            weatherRuntimeDao.queryRaw("delete from detailDayTemp where id = " + dayWeather.getId());
        }

        weatherRuntimeDao.queryRaw("delete from description where id = " + weather.getFirstWeather().getId());
        weatherRuntimeDao.queryRaw("delete from sys where idDB = " + weather.getSys().getId());
        weatherRuntimeDao.queryRaw("delete from temperature where id = " + weather.getTemperature().getId());
        weatherRuntimeDao.queryRaw("delete from wind where id = " + weather.getWind().getId());
        weatherRuntimeDao.queryRaw("delete from weather where id = " + weather.getId());
    }


    public void deleteAllWeather() {
        weatherRuntimeDao.queryRaw("delete from weather");
        weatherRuntimeDao.queryRaw("delete from clouds");
        weatherRuntimeDao.queryRaw("delete from coordinates");
        weatherRuntimeDao.queryRaw("delete from dayTemp");
        weatherRuntimeDao.queryRaw("delete from description");
        weatherRuntimeDao.queryRaw("delete from detailDayTemp");
        weatherRuntimeDao.queryRaw("delete from rain");
        weatherRuntimeDao.queryRaw("delete from snow");
        weatherRuntimeDao.queryRaw("delete from sys");
        weatherRuntimeDao.queryRaw("delete from wind");
    }

    public List<Weather> getAllWeathers() {
        return weatherRuntimeDao.queryForAll();
    }

    public Weather getWeatherById(int id) {
        Weather weather = null;
        try {
            QueryBuilder<Weather, Integer> queryBuilder = weatherRuntimeDao.queryBuilder();
            queryBuilder.where().eq("id", id);
            PreparedQuery<Weather> preparedQuery = queryBuilder.prepare();
            List<Weather> weathers = weatherRuntimeDao.query(preparedQuery);
            weather = weathers.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weather;
    }

    public Weather getWeatherByCityName(String cityName) {
        Weather weather = null;
        try {
            QueryBuilder<Weather, Integer> queryBuilder = weatherRuntimeDao.queryBuilder();
            queryBuilder.where().eq("name", cityName);
            PreparedQuery<Weather> preparedQuery = queryBuilder.prepare();
            List<Weather> weathers = weatherRuntimeDao.query(preparedQuery);
            weather = weathers.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weather;
    }

    public List<String> getCitiesId() {
        List<String> resultList = new ArrayList<>();
        try {
            QueryBuilder<Weather, Integer> queryBuilder = weatherRuntimeDao.queryBuilder();
            queryBuilder.selectColumns("id");
            GenericRawResults<String[]> rawResults = weatherRuntimeDao.queryRaw(queryBuilder.prepareStatementString());
            for (String[] IdItem : rawResults) {
                resultList.add(IdItem[0]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }


}
