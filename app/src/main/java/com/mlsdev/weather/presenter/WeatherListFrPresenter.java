package com.mlsdev.weather.presenter;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.services.impl.net.WeatherNetworkService;
import com.mlsdev.weather.services.impl.net.listeners.IGetWeatherByCity;
import com.mlsdev.weather.ui.fragment.IWeatherListFr;
import com.mlsdev.weather.ui.fragment.impl.WeatherListFr;
import com.mlsdev.weather.ui.model.WeatherItem;

import java.util.ArrayList;
import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 09.02.15.
 */
public class WeatherListFrPresenter implements IGetWeatherByCity {

    private IWeatherListFr iWeatherListFr;
    private WeatherNetworkService weatherNetworkService = new WeatherNetworkService(this);

    public WeatherListFrPresenter(IWeatherListFr iWeatherListFr) {
        this.iWeatherListFr = iWeatherListFr;
    }

    public void updateWeatherList() {
        iWeatherListFr.updateWeatherList(getAllWeatherItem());
    }

    public void updateWeatherList(List<WeatherItem> weatherItems) {
        iWeatherListFr.updateWeatherList(weatherItems);
    }

    public void showDeletingCheckBox() {
        iWeatherListFr.showDeletingCheckBox();
    }

    public void hideDeletingCheckBox() {
        iWeatherListFr.hideDeletingCheckBox();
    }

    public void updateMenu() {
        iWeatherListFr.updateMenu();
    }

    public void showAddWeatherItemDialog() {
        iWeatherListFr.showAddWeatherItemDialog();
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
        ServiceManager.getWeatherService().deleteAllWeather(weathers);
        hideDeletingCheckBox();
        updateMenu();
        updateWeatherList(weatherItems);
    }

    public List<WeatherItem> getAllWeatherItem() {
        List<WeatherItem> resultList = new ArrayList<>();
        List<Weather> weathers = ServiceManager.getWeatherService().getAllWeathers();
        for (Weather weather : weathers) {
            resultList.add(new WeatherItem(weather, false));
        }
        return resultList;
    }

    public void loadWeather(String cityName) {
        WeatherListFr context = (WeatherListFr) iWeatherListFr;
        iWeatherListFr.showProgressDialog(context.getString(R.string.pb_load_weather_tittle), context.getString(R.string.pb_wait_message));
        weatherNetworkService.getWeatherByCity(cityName);
    }

    @Override
    public void onSuccessGetWeatherByCity(Weather weather) {
        ServiceManager.getWeatherService().createOrUpdateWeather(weather);
        iWeatherListFr.onSuccessAddWeather();
        iWeatherListFr.dismissProgressDialog();
    }

    @Override
    public void onErrorGetWeatherByCity(String error) {
        iWeatherListFr.dismissProgressDialog();
        iWeatherListFr.onErrorAddWeather(error);
    }
}
