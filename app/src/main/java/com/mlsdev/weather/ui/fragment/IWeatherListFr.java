package com.mlsdev.weather.ui.fragment;

import com.mlsdev.weather.ui.model.WeatherItem;

import java.util.List;

/**
 * Created by romakukhar on 09.02.15.
 */
public interface IWeatherListFr extends BaseFragment {

    public void updateWeatherList(List<WeatherItem> weatherItems);

    public void showAddWeatherItemDialog();

    public void onErrorAddWeather(String error);

    public void onSuccessAddWeather();

    public void showDeletingCheckBox();

    public void hideDeletingCheckBox();

    public void updateMenu();

}
