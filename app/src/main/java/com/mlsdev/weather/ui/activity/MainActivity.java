package com.mlsdev.weather.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.mlsdev.weather.ui.fragment.WeatherListFragment;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 29.01.15.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showWeatherListFragment();
    }

    @Override
    protected int getLayout() {
//        return R.layout.main_weather_layout;
        return R.layout.detail_weather_layout;
    }


    private void showWeatherListFragment() {
        WeatherListFragment weatherListFragment = new WeatherListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction().add(weatherListFragment, "");
        ft.commit();
    }
}
