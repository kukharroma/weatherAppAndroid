package com.mlsdev.weather.app;

import android.app.Application;

import com.mlsdev.weather.util.CoreApp;

/**
 * Created by romakukhar on 31.01.15.
 */
public class WeatherApp extends Application {

    private static WeatherApp app;

    public static Application getInstance() {
        if (app == null) {
            app = new WeatherApp();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        CoreApp.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
