package com.mlsdev.weather.util;

import android.graphics.drawable.Drawable;

import com.mlsdev.weather.app.WeatherApp;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 17.02.15.
 */
public class WeatherImageUtil {

    public static Drawable getImageByName(String imageName) {
        switch (imageName) {
            case "01d":
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.sunny);
            case "02d" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.cloudy3);
            case "03d" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.cloudy5);
            case "04d" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.overcast);
            case "09d" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.shower3);
            case "10d" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.shower2);
            case "11d" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.tstorm3);
            case "13d" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.snow5);
            case "50d" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.fog);
            case "01n":
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.sunny_night);
            case "02n" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.cloudy3_night);
            case "03n" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.cloudy2_night);
            case "04n" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.cloudy4_night);
            case "09n" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.shower3);
            case "10n" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.shower2_night);
            case "11n" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.tstorm2_night);
            case "13n" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.snow3_night);
            case "50n" :
                return WeatherApp.getInstance().getResources().getDrawable(R.drawable.fog_night);
            default:
                return  WeatherApp.getInstance().getResources().getDrawable(R.drawable.dunno);
        }

    }
}
