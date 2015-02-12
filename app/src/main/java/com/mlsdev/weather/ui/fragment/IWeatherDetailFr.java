package com.mlsdev.weather.ui.fragment;

import com.mlsdev.weather.model.Weather;

/**
 * Created by romakukhar on 09.02.15.
 */
public interface IWeatherDetailFr extends BaseFragment {

    public void updateDetailWeather(Weather weather);

    public void showProgressBar();

    public void dismissProgressBar();

    public void showChart();

    public void dismissChart();

}
