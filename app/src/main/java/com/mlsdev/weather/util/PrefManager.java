package com.mlsdev.weather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mlsdev.weather.app.WeatherApp;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 31.01.15.
 */
public class PrefManager {

    private static SharedPreferences sharedPreferences;
    private static Context cntx;

    public static SharedPreferences getPref() {
        if (sharedPreferences == null) {
            init(WeatherApp.getInstance());
        }
        return sharedPreferences;
    }

    public static void init(Context context) {
        cntx = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_pref), Context.MODE_PRIVATE);
    }

    public static String getAmountOfDaysDailyForecast() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cntx);
        return preferences.getString(cntx.getString(R.string.amountOfDaysKey), cntx.getString(R.string.defaultAmountOfDays));
    }


}
