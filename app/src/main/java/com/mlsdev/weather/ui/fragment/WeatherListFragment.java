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
import com.mlsdev.weather.services.impl.net.WeatherNetworkService;
import com.mlsdev.weather.services.impl.net.listeners.IGetAllWeatherById;
import com.mlsdev.weather.services.impl.net.listeners.IGetWeatherByCity;
import com.mlsdev.weather.ui.activity.DetailWeatherActivity;
import com.mlsdev.weather.ui.adapters.WeatherItemAdapter;
import com.mlsdev.weather.ui.dialogs.AddWeatherItemDialog;
import com.mlsdev.weather.ui.model.WeatherItem;

import java.util.ArrayList;
import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 03.02.15.
 */
public class WeatherListFragment extends Fragment implements IGetWeatherByCity {

    private List<Weather> weathers;
    private List<WeatherItem> weatherItemList = new ArrayList<>();
    ;

    private ListView lvWeather;
    private WeatherItemAdapter adapter;
    private ProgressDialog progressDialog;
    private AddWeatherItemDialog dialog;

    private static final int ADD_CITY_REQUEST_CODE = 1;
    public static boolean IS_ANY_ITEM_DELETE_CHECKED;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        weathers = ServiceManager.getWeatherService().getAllWeathers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_list_layout, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        lvWeather = (ListView) view.findViewById(R.id.lv_weather);
        if (!weathers.isEmpty()) {
            for (Weather weather : weathers) {
                WeatherItem item = new WeatherItem(weather, false);
                weatherItemList.add(item);
            }
            adapter = new WeatherItemAdapter(getActivity(), this, weatherItemList, R.layout.weather_list_item);
            lvWeather.setAdapter(adapter);

            lvWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), DetailWeatherActivity.class);
                    intent.putExtra(DetailWeatherActivity.CURRENT_ITEM, position);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getActivity().getMenuInflater();
        if (IS_ANY_ITEM_DELETE_CHECKED) {
            inflater.inflate(R.menu.delete_item_done_menu, menu);
        } else {
            inflater.inflate(R.menu.main_weather_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item_action:
                showAddWeatherItemDialog();
                break;
            case R.id.delete_item_action:
                showDeletingCheckBox();
                break;
            case R.id.done_item_action:
                deleteCheckedItem();
                break;
            case R.id.main_action_settings:
                Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_update_all:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddWeatherItemDialog() {
        dialog = new AddWeatherItemDialog();
        dialog.setTargetFragment(this, ADD_CITY_REQUEST_CODE);
        dialog.show(getFragmentManager(), "");
    }

    private void showDeletingCheckBox() {
        adapter.showDeletingCheckBox();
    }

    private void deleteCheckedItem() {
        adapter.deleteCheckedItems();
    }

    private void updateAllWeathers() {
        List<String> citiesId = new ArrayList<>();
        for (Weather weather : weathers) {
            citiesId.add(String.valueOf(weather.getId()));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ADD_CITY_REQUEST_CODE:
                    WeatherNetworkService weatherNetworkService = new WeatherNetworkService(this);
                    weatherNetworkService.getWeatherByCity(data.getStringExtra(AddWeatherItemDialog.CITY_NAME));
                    showDialog(getString(R.string.pb_load_weather_tittle), getString(R.string.pb_loading_message));
                    break;
            }
        }
    }

    @Override
    public void onSuccessGetWeatherByCity(Weather weather) {
        ServiceManager.getWeatherService().saveWeather(weather);
        WeatherItem item = new WeatherItem(weather, false);
        weatherItemList.add(item);
        weathers.add(weather);
        if (adapter == null) {
            adapter = new WeatherItemAdapter(getActivity(), this, weatherItemList, R.layout.weather_list_item);
            adapter.updateList(weatherItemList);
            lvWeather.setAdapter(adapter);
        } else {
            adapter.updateList(weatherItemList);
        }
        dismissDialogs();
    }

    @Override
    public void onErrorGetWeatherByCity(String error) {
        dismissDialogs();
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }



    private void showDialog(String tittle, String message) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(tittle);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    private void dismissDialogs() {
        progressDialog.dismiss();
        dialog.dismiss();
    }
}
