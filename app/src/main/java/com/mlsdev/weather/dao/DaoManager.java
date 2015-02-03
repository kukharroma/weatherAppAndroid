package com.mlsdev.weather.dao;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.mlsdev.weather.app.WeatherApp;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.util.DBConfig;

/**
 * Created by romakukhar on 30.01.15.
 */
public class DaoManager {

    private static DaoManager daoManager = null;
    private static DatabaseHelper databaseHelper = null;

    private static RuntimeExceptionDao<Weather, Integer> weatherDao;

    public DaoManager getManager() {
        if (daoManager == null || databaseHelper == null) {
            init(WeatherApp.getInstance());
        }
        return daoManager;
    }

    public static void init(Context context) {
        daoManager = new DaoManager();
        databaseHelper = new DatabaseHelper(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    public static RuntimeExceptionDao<Weather, Integer> getWeatherRuntimeDao() {
        if (weatherDao == null) {
            weatherDao = getDatabaseHelper().getRuntimeExceptionDao(Weather.class);
        }
        return weatherDao;
    }

    public static DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}
