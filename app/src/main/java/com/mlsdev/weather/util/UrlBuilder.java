package com.mlsdev.weather.util;

import android.net.Uri;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public class UrlBuilder {

    private static final String QUERY_Q = "q";
    private static final String QUERY_ID = "id";
    private static final String QUERY_UNITS = "units";
    private static final String UNITS = "metric";
    private static final String WEATHER = "weather";
    private static final String GROUP = "group";
    private static final String DAILY = "daily";
    private static final String FORECAST = "forecast";
    private static final String COUNT = "cnt";

    public static String getWeatherUrlByCity(String cityName) {
        Uri uriBuilder = Uri.parse(WeatherApi.MAIN_URL + WEATHER).buildUpon()
                .appendQueryParameter(QUERY_Q, cityName)
                .appendQueryParameter(QUERY_UNITS, UNITS)
                .build();
        return uriBuilder.toString();
    }

    public static String getWeatherUrlById(int id) {
        Uri uriBuilder = Uri.parse(WeatherApi.MAIN_URL + WEATHER).buildUpon()
                .appendQueryParameter(QUERY_ID, String.valueOf(id))
                .appendQueryParameter(QUERY_UNITS, UNITS)
                .build();
        return uriBuilder.toString();
    }

    public static String getWeatherUrlByCitiesId(List<String> citiesId) {
        Uri uriBuilder = Uri.parse(WeatherApi.MAIN_URL + GROUP).buildUpon()
                .appendQueryParameter(QUERY_ID, getStringWithAllId(citiesId))
                .appendQueryParameter(QUERY_UNITS, UNITS)
                .build();
        return uriBuilder.toString();
    }

    public static String getDailyWeather(String cityId, int dayCount) {
        Uri uriBuilder = Uri.parse(WeatherApi.MAIN_URL + FORECAST + "/" + DAILY).buildUpon()
                .appendQueryParameter(QUERY_ID, cityId)
                .appendQueryParameter(COUNT, String.valueOf(dayCount))
                .appendQueryParameter(QUERY_UNITS, UNITS)
                .build();
        return uriBuilder.toString();
    }

    private static String getStringWithAllId(List<String> citiesId) {
        String result = "";
        for (String id : citiesId) {
            result += id + ",";
        }
        return result.substring(0, result.length() - 1);
    }
}
