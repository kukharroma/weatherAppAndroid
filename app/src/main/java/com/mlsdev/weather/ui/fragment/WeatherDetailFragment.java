package com.mlsdev.weather.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mlsdev.weather.ui.adapters.WeatherItemAdapter;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 04.02.15.
 */
public class WeatherDetailFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_weather_layout, container, false);

        return view;
    }
}
