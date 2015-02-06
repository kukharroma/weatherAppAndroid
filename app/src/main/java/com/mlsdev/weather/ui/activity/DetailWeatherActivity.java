package com.mlsdev.weather.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.ui.fragment.WeatherDetailFragment;

import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 05.02.15.
 */
public class DetailWeatherActivity extends BaseActivity {

    private static int PAGES_NUMBER;
    private List<Weather> weatherList;
    public static String CURRENT_ITEM = "CURRENT_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initList();
        initComponents();
    }

    private void initList() {
        weatherList = ServiceManager.getWeatherService().getAllWeathers();
        PAGES_NUMBER = weatherList.size();
    }

    private void initComponents() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.detail_weather_pager);
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        viewPager.setCurrentItem(getIntent().getIntExtra(CURRENT_ITEM, 0));
        viewPager.setAdapter(pagerAdapter);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.detail_weather_container_layout;
    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Weather weather = weatherList.get(position);
            return new WeatherDetailFragment(weather);
        }

        @Override
        public int getCount() {
            return PAGES_NUMBER;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_weather_menu, menu);
        return true;
    }
}
