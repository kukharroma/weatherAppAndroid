package com.mlsdev.weather.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.presenter.WeatherDetailFrPresenter;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.ui.fragment.impl.WeatherDetailFr;

import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 05.02.15.
 */
public class DetailWeatherActivity extends BaseActivity {

    private static int PAGES_NUMBER;
    private List<Weather> weatherList;
    public static String CURRENT_ITEM = "CURRENT_ITEM";
    private PagerAdapter pagerAdapter;
    private ProgressDialog progressDialog;

    private static int CURRENT_POSITION = 0;

    private WeatherDetailFrPresenter presenter = new WeatherDetailFrPresenter(this);

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
        pagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_weather_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_action_settings:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_item_action:
                presenter.updateDailyWeather(weatherList.get(CURRENT_POSITION));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Weather weather = weatherList.get(position);
            CURRENT_POSITION = position - 1;
            return new WeatherDetailFr(weather);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return PAGES_NUMBER;
        }
    }

    public void updateViewPager(Weather weather) {
        weatherList.remove(CURRENT_POSITION);
        weatherList.add(weather);
        pagerAdapter.notifyDataSetChanged();
    }

    public void showProgressDialog(String tittle, String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(tittle);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }
}
