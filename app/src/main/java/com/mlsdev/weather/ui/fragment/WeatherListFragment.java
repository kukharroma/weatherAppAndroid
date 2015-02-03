package com.mlsdev.weather.ui.fragment;

import android.app.ListFragment;
import android.os.Bundle;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.ui.adapters.WeatherItemAdapter;

import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 03.02.15.
 */
public class WeatherListFragment extends ListFragment {

    private List<Weather> weatherList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            weatherList = savedInstanceState.getParcelableArrayList("weatherList");
            if (!weatherList.isEmpty()) {
                WeatherItemAdapter adapter = new WeatherItemAdapter(getActivity(), weatherList, R.layout.weather_list_item);
                adapter.updateList(null);
                setListAdapter(adapter);
            }
        }
    }
}
