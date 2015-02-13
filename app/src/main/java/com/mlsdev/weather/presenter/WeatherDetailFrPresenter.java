package com.mlsdev.weather.presenter;

import android.widget.Toast;

import com.mlsdev.weather.model.DailyWeatherList;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.services.impl.net.WeatherNetworkService;
import com.mlsdev.weather.services.impl.net.listeners.WeatherDailyListener;
import com.mlsdev.weather.ui.activity.DetailWeatherActivity;
import com.mlsdev.weather.ui.fragment.impl.WeatherDetailFr;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 11.02.15.
 */
public class WeatherDetailFrPresenter implements WeatherDailyListener {

    private DetailWeatherActivity activity;
    private WeatherNetworkService weatherNetworkService;
    private Weather weather;
    private WeatherDetailFr weatherDetailFr;

    public WeatherDetailFrPresenter(DetailWeatherActivity activity) {
        this.activity = activity;
        weatherNetworkService = new WeatherNetworkService(this);
    }

    public WeatherDetailFrPresenter(WeatherDetailFr weatherDetailFr) {
        this.weatherDetailFr = weatherDetailFr;
        weatherNetworkService = new WeatherNetworkService(this);
    }

    public void updateDailyWeather(Weather weather) {
        this.weather = weather;
        activity.showProgressDialog(activity.getString(R.string.pb_load_weather_tittle), activity.getString(R.string.pb_wait_message));
        weatherNetworkService.getDailyWeather(String.valueOf(weather.getId()), 7, false);
    }

    @Override
    public void onSuccessGetDailyWeather(DailyWeatherList dailyList) {
        weather.getDayTempList().clear();
        weather.getDayTempList().addAll(dailyList.getList());
        ServiceManager.getWeatherService().createOrUpdateWeather(weather);
        activity.updateViewPager(weather);
        activity.dismissProgressDialog();
    }

    @Override
    public void onErrorGetDailyWeather(String error) {
        activity.dismissProgressDialog();
    }

    public void firstLoadDailyWeather(Weather weather) {
        this.weather = weather;
        weatherDetailFr.dismissChart();
        weatherDetailFr.showProgressBar();
        weatherNetworkService.getDailyWeather(String.valueOf(weather.getId()), 7, true);
    }

    @Override
    public void onSuccessFirstLoadDailyWeather(DailyWeatherList dailyList) {
        weather.getDayTempList().clear();
        weather.getDayTempList().addAll(dailyList.getList());
        ServiceManager.getWeatherService().createOrUpdateWeather(weather);
        weatherDetailFr.updateDetailWeather(weather);
    }

    @Override
    public void onErrorFirstLoadDailyWeather(String error) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show();
    }
}
