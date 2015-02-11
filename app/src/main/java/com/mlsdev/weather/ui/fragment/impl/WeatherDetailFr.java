package com.mlsdev.weather.ui.fragment.impl;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
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
    private LineChart chart;
    private TextView tvLocation, tvMainTemp, tvMainWeather, tvMainWeatherDescription, tvMinMaxTemp;
    private ProgressBar progressBar;

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
        chart = (LineChart) view.findViewById(R.id.chart);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    @Override
    public void fillComponents() {
        tvLocation.setText(weather.getCity() + " ," + weather.getSys().getCountry());
        tvMainTemp.setText(String.valueOf(weather.getTemperature().getTemp()) + getString(R.string.degree));
        tvMainWeather.setText(weather.getFirstWeater().getMain());
        tvMainWeatherDescription.setText(weather.getFirstWeater().getDescription());
        tvMinMaxTemp.setText(weather.getTemperature().getMinTemp() + getString(R.string.degree) + " / " + weather.getTemperature().getMaxTemp() + getString(R.string.degree));

        if (weather.getDayTempList().isEmpty()) {
            showProgressBar();
            chart.setVisibility(View.GONE);
        } else {
            dismissProgressBar();
            createChart();
        }
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

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void createChart() {
        ArrayList<String> strings = new ArrayList<>();

        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");

        ArrayList<Entry> arrayList = new ArrayList<>();

        arrayList.add(new Entry(2.5f, 0));
        arrayList.add(new Entry(9.5f, 1));
        arrayList.add(new Entry(4.5f, 2));
        arrayList.add(new Entry(-4.5f, 3));
        arrayList.add(new Entry(-8.5f, 4));
        arrayList.add(new Entry(-2.5f, 5));
        arrayList.add(new Entry(-3.5f, 6));

        LineDataSet set = new LineDataSet(arrayList, "set1");
        set.setLineWidth(3f);
        set.setCircleSize(5f);
        set.setCircleColor(getActivity().getResources().getColor(R.color.red));

        LineData data = new LineData(strings, set);
        chart.setDescription("Updated");
        chart.setData(data);

        chart.setStartAtZero(false);

        Legend l = chart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);

        XLabels xl = chart.getXLabels();
        xl.setPosition(XLabels.XLabelPosition.BOTTOM); // set the position
        xl.setTextSize(12f); // set the textsize
        xl.setSpaceBetweenLabels(3);


        YLabels yl = chart.getYLabels();
        yl.setPosition(YLabels.YLabelPosition.LEFT); // set the position
        yl.setTextSize(12f); // set the textsize
    }
}
