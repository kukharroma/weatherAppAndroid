package com.mlsdev.weather.ui.activity;

import android.os.Bundle;

import com.mlsdev.weather.ui.fragment.impl.WeatherListFr;

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
        return R.layout.main_weather_layout;
    }

    private void showWeatherListFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, new WeatherListFr(), WeatherListFr.class.getName()).commit();
    }
    
}
