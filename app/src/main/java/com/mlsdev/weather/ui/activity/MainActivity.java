package com.mlsdev.weather.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.mlsdev.weather.dao.DaoManager;
import com.mlsdev.weather.model.Description;
import com.mlsdev.weather.model.Sys;
import com.mlsdev.weather.model.Temperature;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.ui.fragment.MainLoadFragment;
import com.mlsdev.weather.ui.fragment.WeatherListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romakukhar on 29.01.15.
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        List<Weather> list = ServiceManager.getWeatherService().getAllWeathers();
//        showWeatherListFragment(list);



        Weather weather = new Weather();
        weather.setCity("Vinnitza");
        weather.setWeatherTime("123456789");

        Sys sys = new Sys();
        sys.setCountry("UKR");

        weather.setSys(sys);

        Description description = new Description();
        description.setMain("Clouds");
        description.setDescription("Big clouds");

//        Description[] descriptions = new Description[1];
//        descriptions[0] = description;
//
//        weather.setWeather(descriptions);

        Temperature temperature = new Temperature();
        temperature.setMinTemp(2);
        temperature.setMaxTemp(8);
        temperature.setTemp("6");

        weather.setTemperature(temperature);

        ServiceManager.getWeatherService().saveWeather(weather);

    }

    private void showLoadFragment() {
        MainLoadFragment mainLoadFragment = new MainLoadFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction().add(mainLoadFragment, "").addToBackStack("");
        ft.commit();

    }

    private void showWeatherListFragment(List<Weather> weatherList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("weatherList", new ArrayList(weatherList));
        WeatherListFragment weatherListFragment = new WeatherListFragment();
        weatherListFragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction().add(weatherListFragment, "").addToBackStack("");
        ft.commit();
    }
}
