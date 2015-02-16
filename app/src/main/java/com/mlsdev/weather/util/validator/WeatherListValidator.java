package com.mlsdev.weather.util.validator;

import com.mlsdev.weather.app.WeatherApp;
import com.mlsdev.weather.services.impl.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 16.02.15.
 */
public class WeatherListValidator {

    private static WeatherListValidator validator;

    private WeatherListValidator() {
        kievsList.add("kiev");
        kievsList.add("kyiv");
    }

    public static WeatherListValidator getInstance() {
        if (validator == null) {
            validator = new WeatherListValidator();
        }
        return validator;
    }

    private List<String> kievsList = new ArrayList<>();

    public String validate(String cityName) {
        List<String> resultList = ServiceManager.getWeatherService().getCitiesNames();
        if (kievsList.contains(cityName.toLowerCase())) {
            return WeatherApp.getInstance().getString(R.string.do_not_input_kiev);
        } else if (resultList.contains(cityName)) {
            return WeatherApp.getInstance().getString(R.string.city_exists);
        } else {
            return null;
        }
    }
}
