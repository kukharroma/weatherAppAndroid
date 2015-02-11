package com.mlsdev.weather.presenter;

import com.mlsdev.weather.model.DailyWeatherList;
import com.mlsdev.weather.services.impl.net.listeners.WeatherDailyListener;
import com.mlsdev.weather.ui.fragment.impl.WeatherDetailFr;

/**
 * Created by romakukhar on 11.02.15.
 */
public class WeatherDetailFrPresenter implements WeatherDailyListener {

    private WeatherDetailFr weatherDetailFr;

    public WeatherDetailFrPresenter(WeatherDetailFr weatherDetailFr) {
        this.weatherDetailFr = weatherDetailFr;
    }

    @Override
    public void onSuccessGetDailyWeather(DailyWeatherList dailyList) {

    }

    @Override
    public void onErrorGetDailyWeather(String error) {

    }
}
