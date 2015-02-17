package com.mlsdev.weather.ui.utils;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.mlsdev.weather.app.WeatherApp;
import com.mlsdev.weather.model.DayWeather;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.util.DateUtil;

import java.util.ArrayList;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 16.02.15.
 */
public class ChartUtil {

    private static ChartUtil instance = null;

    private ChartUtil() {

    }

    public static ChartUtil getInstance() {
        if (instance == null) {
            instance = new ChartUtil();
        }
        return instance;
    }

    public void drawWeatherChart(LineChart chart, Weather weather) {

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Entry> arrayList = new ArrayList<>();

        for (DayWeather day : weather.getDayTempList()) {
            strings.add(DateUtil.getDayName(day.getDate()));
            arrayList.add(new Entry((float) day.getDetailDayWeatherTemp().getDay(), arrayList.size()));
        }

        LineDataSet set = new LineDataSet(arrayList, WeatherApp.getInstance().getString(R.string.temp_for_next) + " " + strings.size() + WeatherApp.getInstance().getString(R.string.days));
        set.setLineWidth(3f);
        set.setCircleSize(5f);
        set.setCircleColor(WeatherApp.getInstance().getResources().getColor(R.color.red));

        LineData data = new LineData(strings, set);
        chart.setDescription(WeatherApp.getInstance().getString(R.string.updated) + weather.getFormattedHourDate());
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
        yl.setTextSize(20f); // set the textsize


        chart.setValueTextSize(12f);
        chart.setDrawYLabels(false);
    }
}
