package com.mlsdev.weather.ui.fragment.impl;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.ui.fragment.IWeatherDetailFr;

import java.util.ArrayList;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 04.02.15.
 */
public class WeatherDetailFr extends Fragment implements IWeatherDetailFr {

    private Weather weather;
    private ProgressDialog progressDialog;
    private TextView tvLocation, tvMainTemp, tvMainWeather, tvMainWeatherDescription, tvMinMaxTemp;

    public WeatherDetailFr(Weather weather) {
        this.weather = weather;
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
        LineChart chart = (LineChart) view.findViewById(R.id.chart);

        ArrayList<String> strings = new ArrayList<>();

        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");


        Entry entry = new Entry(2.5f, 0);
        Entry entry1 = new Entry(1.5f, 1);
        Entry entry3 = new Entry(4.5f, 2);
        Entry entry4 = new Entry(4.5f, 3);
        Entry entry5 = new Entry(4.5f, 4);
        Entry entry6 = new Entry(4.5f, 5);
        Entry entry7 = new Entry(4.5f, 6);

        ArrayList<Entry> arrayList = new ArrayList<>();
        arrayList.add(entry);
        arrayList.add(entry1);
        arrayList.add(entry3);
        arrayList.add(entry4);
        arrayList.add(entry5);
        arrayList.add(entry6);
        arrayList.add(entry7);

        LineDataSet set = new LineDataSet(arrayList, "LABEL");

        LineData data = new LineData(strings, set);

        chart.setTouchEnabled(false);
        chart.setDrawYValues(false);
        chart.setDescription("Updated");

        chart.animateXY(3000, 3000);
        chart.setData(data);
    }

    @Override
    public void fillComponents() {
        tvLocation.setText(weather.getCity() + " ," + weather.getSys().getCountry());
        tvMainTemp.setText(String.valueOf(weather.getTemperature().getTemp()) + getString(R.string.degree));
        tvMainWeather.setText(weather.getFirstWeater().getMain());
        tvMainWeatherDescription.setText(weather.getFirstWeater().getDescription());
        tvMinMaxTemp.setText(weather.getTemperature().getMinTemp() + getString(R.string.degree) + " / " + weather.getTemperature().getMaxTemp() + getString(R.string.degree));

    }


    @Override
    public void updateDetailWeather() {

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
}
