package com.mlsdev.weather.ui.fragment.impl;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mlsdev.weather.presenter.WeatherListFrPresenter;
import com.mlsdev.weather.ui.activity.DetailWeatherActivity;
import com.mlsdev.weather.ui.adapters.WeatherItemAdapter;
import com.mlsdev.weather.ui.dialogs.AddWeatherItemDialog;
import com.mlsdev.weather.ui.fragment.IWeatherListFr;
import com.mlsdev.weather.ui.model.WeatherItem;

import java.util.ArrayList;
import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 03.02.15.
 */
public class WeatherListFr extends Fragment implements IWeatherListFr {

    private List<WeatherItem> weatherItemList = new ArrayList<>();
    private WeatherListFrPresenter presenter = new WeatherListFrPresenter(this);

    private ListView lvWeather;
    private WeatherItemAdapter adapter;
    private ProgressDialog progressDialog;
    private AddWeatherItemDialog dialog;

    private static final int ADD_CITY_REQUEST_CODE = 1;
    public static boolean IS_ANY_ITEM_DELETE_CHECKED;
    public static boolean IS_ANY_CITY_ADDED;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_list_layout, container, false);
        initComponents(view);
        fillComponents();
        return view;
    }

    @Override
    public void initComponents(View view) {
        lvWeather = (ListView) view.findViewById(R.id.lv_weather);
        weatherItemList = presenter.getAllWeatherItem();
        adapter = new WeatherItemAdapter(getActivity(), this, weatherItemList, R.layout.weather_list_item);
    }

    @Override
    public void fillComponents() {
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

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (IS_ANY_ITEM_DELETE_CHECKED) {
            getActivity().getMenuInflater().inflate(R.menu.delete_item_done_menu, menu);
        } else if (IS_ANY_CITY_ADDED) {
            getActivity().getMenuInflater().inflate(R.menu.delete_or_update_all_menu, menu);
        } else {
            getActivity().getMenuInflater().inflate(R.menu.main_weather_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item_action:
                presenter.showAddWeatherItemDialog();
                break;
            case R.id.delete_item_action:
                presenter.showDeletingCheckBox();
                break;
            case R.id.done_item_action:
                presenter.deleteCheckedItems(weatherItemList);
                break;
            case R.id.main_action_settings:
//                Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_update_all:
                presenter.loadAllWeathers();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateWeatherList(List<WeatherItem> weatherItems) {
        getActivity().invalidateOptionsMenu();
        weatherItemList = weatherItems;
        adapter.updateList(weatherItems);
    }

    @Override
    public void showAddWeatherItemDialog() {
        dialog = new AddWeatherItemDialog();
        dialog.setTargetFragment(this, ADD_CITY_REQUEST_CODE);
        dialog.show(getFragmentManager(), "");
    }

    @Override
    public void showDeletingCheckBox() {
        adapter.showDeletingCheckBox();
    }

    @Override
    public void hideDeletingCheckBox() {
        adapter.hideDeletingCheckBox();
    }

    @Override
    public void onDeleteWeather() {
        adapter.showOrHideDoneActionIcon();
    }

    @Override
    public void onSuccessAddWeather() {
        presenter.updateWeatherList();
    }

    @Override
    public void onErrorAddWeather(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessUpdateAllWeather() {
        presenter.updateWeatherList();
    }

    @Override
    public void onErrorUpdateAllWeather(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    public void updateMenu() {
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void showProgressDialog(String tittle, String message) {
        progressDialog = new ProgressDialog(getActivity(), R.style.progressDialogStyle);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ADD_CITY_REQUEST_CODE:
                    presenter.loadWeather(data.getStringExtra(AddWeatherItemDialog.CITY_NAME));
            }
        }
    }
}
