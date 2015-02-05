package com.mlsdev.weather.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.ServiceManager;
import com.mlsdev.weather.services.impl.net.listeners.IGetWeatherByCity;
import com.mlsdev.weather.ui.adapters.WeatherItemAdapter;
import com.mlsdev.weather.ui.dialogs.AddWeatherItemDialog;

import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 03.02.15.
 */
public class WeatherListFragment extends Fragment implements IGetWeatherByCity {

    private List<Weather> weatherList;
    private ListView lvWeather;

    private ProgressDialog progressDialog;

    private static final int ADD_CITY_REQUEST_CODE = 1;

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

            lvWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), DetailWeatherFragmentActivity.class);
                    intent.putExtra(DetailWeatherFragmentActivity.CURRENT_ITEM, position);
                    startActivity(intent);
                }
            });
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
                showAddWeatherItemDialog();
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

    private void showAddWeatherItemDialog() {
        AddWeatherItemDialog dialog = new AddWeatherItemDialog();
        dialog.setTargetFragment(this, ADD_CITY_REQUEST_CODE);
        dialog.show(getFragmentManager(), "");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_CITY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    ServiceManager.getWeatherNetworkService(getActivity()).getWeatherByCity(data.getStringExtra(AddWeatherItemDialog.CITY_NAME));
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setCancelable(false);
                    progressDialog.setTitle(getString(R.string.progress_bar_load_weather));
                    progressDialog.setMessage(getString(R.string.progress_bar_wait));
                    progressDialog.show();
                }
        }
    }

    @Override
    public void onSuccessGetWeatherByCity(Weather weather) {
        progressDialog.dismiss();
    }

    @Override
    public void onErrorGetWeatherByCity(String str) {
        progressDialog.dismiss();
    }
}
