package com.mlsdev.weather.presenter;

import android.widget.Toast;

import com.mlsdev.weather.app.WeatherApp;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.model.WeatherList;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.services.impl.net.WeatherNetworkService;
import com.mlsdev.weather.services.impl.net.listeners.BaseWeatherListener;
import com.mlsdev.weather.ui.fragment.IWeatherListFr;
import com.mlsdev.weather.ui.fragment.impl.WeatherListFr;
import com.mlsdev.weather.ui.model.WeatherItem;
import com.mlsdev.weather.util.validator.WeatherListValidator;

import java.util.ArrayList;
import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 09.02.15.
 */
public class WeatherListFrPresenter implements BaseWeatherListener {

    private IWeatherListFr weatherListFr;

    private WeatherNetworkService weatherNetworkService = new WeatherNetworkService(this);

    public WeatherListFrPresenter(IWeatherListFr weatherListFr) {
        this.weatherListFr = weatherListFr;
    }

    public void updateWeatherList() {
        weatherListFr.updateWeatherList(getAllWeatherItem());
    }

    public void showDeletingCheckBox() {
        weatherListFr.showDeletingCheckBox();
    }

    public void showAddWeatherItemDialog() {
        weatherListFr.showAddWeatherItemDialog();
    }

    public void deleteCheckedItems(List<WeatherItem> weatherItems) {
        List<WeatherItem> resultList = new ArrayList<>();
        List<Weather> weathers = new ArrayList<>();

        for (WeatherItem item : weatherItems) {
            if (item.isEnabledDelete()) {
                resultList.add(item);
                weathers.add(item.getWeather());
            }
        }

        weatherItems.removeAll(resultList);
        ServiceManager.getWeatherService().deleteWeatherList(weathers);

        WeatherListFr.IS_ANY_CITY_ADDED = !weatherItems.isEmpty();
        weatherListFr.onDeleteWeather();
        weatherListFr.updateMenu();
        weatherListFr.hideDeletingCheckBox();
        weatherListFr.updateWeatherList(weatherItems);
    }

    public List<WeatherItem> getAllWeatherItem() {
        List<WeatherItem> resultList = new ArrayList<>();
        List<Weather> weathers = ServiceManager.getWeatherService().getAllWeathers();
        for (Weather weather : weathers) {
            resultList.add(new WeatherItem(weather, false));
        }
        WeatherListFr.IS_ANY_CITY_ADDED = !resultList.isEmpty();
        return resultList;
    }

    public void loadWeather(String cityName) {
        String error = WeatherListValidator.getInstance().validate(cityName);
        if (error == null) {
            WeatherListFr context = (WeatherListFr) weatherListFr;
            weatherListFr.showProgressDialog(context.getString(R.string.pb_load_weather_tittle), context.getString(R.string.pb_wait_message));
            weatherNetworkService.getWeatherByCity(cityName);
        } else {
            Toast.makeText(WeatherApp.getInstance(), error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccessGetWeatherByCity(Weather weather) {
        ServiceManager.getWeatherService().createOrUpdateWeather(weather);
        weatherListFr.onSuccessAddWeather();
        weatherListFr.dismissProgressDialog();
    }

    @Override
    public void onErrorGetWeatherByCity(String error) {
        weatherListFr.dismissProgressDialog();
        weatherListFr.onErrorAddWeather(error);
    }

    public void loadAllWeathers() {
        WeatherListFr context = (WeatherListFr) weatherListFr;
        weatherListFr.showProgressDialog(context.getString(R.string.pb_load_weather_tittle), context.getString(R.string.pb_wait_message));
        List<String> citiesId = ServiceManager.getWeatherService().getCitiesId();
        weatherNetworkService.getWeatherByCitiesId(citiesId);
    }

    @Override
    public void onSuccessGetAllWeathers(WeatherList weatherList) {
        ServiceManager.getWeatherService().updateWeathers(weatherList.getList());
        weatherListFr.onSuccessUpdateAllWeather();
        weatherListFr.dismissProgressDialog();
    }

    @Override
    public void onErrorGetAllWeathers(String error) {
        weatherListFr.dismissProgressDialog();
        weatherListFr.onErrorUpdateAllWeather(error);
    }

}
