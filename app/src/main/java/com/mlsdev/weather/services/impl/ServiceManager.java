package com.mlsdev.weather.services.impl;

import android.content.Context;

import com.mlsdev.weather.services.impl.net.WeatherNetworkService;

/**
 * Created by romakukhar on 03.02.15.
 */
public class ServiceManager {

    private static ServiceManager serviceManager;
    private static WeatherService weatherService;

    public static void init(Context context) {
        if (serviceManager == null) {
            serviceManager = new ServiceManager();
        }
    }

    public static WeatherService getWeatherService() {
        if (weatherService == null) {
            weatherService = new WeatherService();
        }
        return weatherService;
    }

}
