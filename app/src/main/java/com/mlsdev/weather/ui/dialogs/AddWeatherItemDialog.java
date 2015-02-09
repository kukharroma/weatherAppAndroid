package com.mlsdev.weather.ui.dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 05.02.15.
 */
public class AddWeatherItemDialog extends DialogFragment {

    private TextView etCity;
    private Button btCity;

    public static String CITY_NAME = "CITY_NAME";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_weather_item_dialog, container, false);
        etCity = (TextView) view.findViewById(R.id.et_city);
        btCity = (Button) view.findViewById(R.id.bt_add_city);
        btCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(CITY_NAME, etCity.getText().toString());
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                dismiss();
            }
        });
        return view;
    }

}
