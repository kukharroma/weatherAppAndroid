package com.mlsdev.weather.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.ui.adapters.WeatherItemAdapter;

import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 03.02.15.
 */
public class WeatherListFragment extends Fragment {

    private List<Weather> weatherList;
    private ListView lvWeather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        weatherList = ServiceManager.getWeatherService().getAllWeathers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_list_layout, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        if (!weatherList.isEmpty()) {
            lvWeather = (ListView) view.findViewById(R.id.lv_weather);
            WeatherItemAdapter adapter = new WeatherItemAdapter(getActivity(), weatherList, R.layout.weather_list_item);
            lvWeather.setAdapter(adapter);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_weather_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item_action:
                Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_item_action:
                Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_action_settings:
                Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
