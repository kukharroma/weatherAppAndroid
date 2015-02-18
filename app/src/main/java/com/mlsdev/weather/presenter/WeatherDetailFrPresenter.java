package com.mlsdev.weather.presenter;

import android.widget.Toast;

import com.mlsdev.weather.model.DailyWeatherList;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.services.impl.net.WeatherNetworkService;
import com.mlsdev.weather.services.impl.net.listeners.WeatherDailyListener;
import com.mlsdev.weather.ui.activity.DetailWeatherActivity;
import com.mlsdev.weather.ui.fragment.impl.WeatherDetailFr;
import com.mlsdev.weather.util.PrefManager;

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
        String amountOfDays = PrefManager.getAmountOfDaysDailyForecast();
        weatherNetworkService.getDailyWeather(String.valueOf(weather.getId()), Integer.parseInt(amountOfDays), false);
    }

    @Override
    public void onSuccessUpdateDailyWeather(DailyWeatherList dailyList) {
        ServiceManager.getWeatherService().createOrUpdateDailyWeather(weather, dailyList.getList());
        activity.updateViewPager();
        activity.dismissProgressDialog();
    }

    @Override
    public void onErrorUpdateDailyWeather(String error) {
        activity.dismissProgressDialog();
        activity.showErrorUpdateDailyWeather(error);
    }

    public void firstLoadDailyWeather(Weather weather) {
        this.weather = weather;
        weatherDetailFr.dismissChart();
        weatherDetailFr.showProgressBar();
        String amountOfDays = PrefManager.getAmountOfDaysDailyForecast();
        weatherNetworkService.getDailyWeather(String.valueOf(weather.getId()), Integer.parseInt(amountOfDays), true);
    }

    @Override
    public void onSuccessFirstLoadDailyWeather(DailyWeatherList dailyList) {
        ServiceManager.getWeatherService().createDailyWeather(weather, dailyList.getList());
        weatherDetailFr.updateDetailWeather();
    }

    @Override
    public void onErrorFirstLoadDailyWeather(String error) {
        Toast.makeText(weatherDetailFr.getActivity(), error, Toast.LENGTH_LONG).show();
    }
}
