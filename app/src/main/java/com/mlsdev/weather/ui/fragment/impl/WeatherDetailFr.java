package com.mlsdev.weather.ui.fragment.impl;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.presenter.WeatherDetailFrPresenter;
import com.mlsdev.weather.ui.activity.DetailWeatherActivity;
import com.mlsdev.weather.ui.fragment.IWeatherDetailFr;
import com.mlsdev.weather.ui.utils.ChartUtil;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 04.02.15.
 */
public class WeatherDetailFr extends Fragment implements IWeatherDetailFr {

    private Weather weather;

    private LineChart chart;
    private TextView tvLocation, tvMainTemp, tvMainWeather, tvMainWeatherDescription, tvMinMaxTemp;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;

    private DetailWeatherActivity activity;

    private WeatherDetailFrPresenter presenter = new WeatherDetailFrPresenter(this);

    public WeatherDetailFr(Weather weather, DetailWeatherActivity activity) {
        this.weather = weather;
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_weather_layout, container, false);
        initComponents(view);
        fillComponents();
        return view;
    }

    @Override
    public void initComponents(View view) {
        tvLocation = (TextView) view.findViewById(R.id.tv_detail_location);
        tvMainTemp = (TextView) view.findViewById(R.id.tv_detail_main_temp);
        tvMainWeather = (TextView) view.findViewById(R.id.tv_detail_main_weather);
        tvMainWeatherDescription = (TextView) view.findViewById(R.id.tv_detail_weather_description);
        tvMinMaxTemp = (TextView) view.findViewById(R.id.tv_detail_min_max_temp);
        chart = (LineChart) view.findViewById(R.id.chart);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    @Override
    public void fillComponents() {
        tvLocation.setText(weather.getCity() + " ," + weather.getSys().getCountry());
        tvMainTemp.setText(String.valueOf(weather.getTemperature().getTemp()) + getString(R.string.degree));
        tvMainWeather.setText(weather.getFirstWeather().getMain());
        tvMainWeatherDescription.setText(weather.getFirstWeather().getDescription());
        tvMinMaxTemp.setText(weather.getTemperature().getMinTemp() + getString(R.string.degree) + " / " + weather.getTemperature().getMaxTemp() + getString(R.string.degree));

        if (weather.getDayTempList().isEmpty()) {
            presenter.firstLoadDailyWeather(weather);
        } else {
            dismissProgressBar();
            createChart(weather);
        }
    }

    @Override
    public void updateDetailWeather(Weather weather) {
        activity.updateViewPager(weather);
    }

    @Override
    public void showProgressDialog(String tittle, String message) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(tittle);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void showChart() {
        chart.setVisibility(View.VISIBLE);
    }

    public void dismissChart() {
        chart.setVisibility(View.GONE);
    }

    private void createChart(Weather weather) {
        ChartUtil.getInstance().drawWeatherChart(chart, weather);
    }
}
