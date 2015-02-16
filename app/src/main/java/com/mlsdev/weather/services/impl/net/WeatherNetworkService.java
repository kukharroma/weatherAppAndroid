package com.mlsdev.weather.services.impl.net;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mlsdev.weather.model.DailyWeatherList;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.model.WeatherList;
import com.mlsdev.weather.services.impl.ServerRequest;
import com.mlsdev.weather.services.impl.net.listeners.BaseWeatherListener;
import com.mlsdev.weather.services.impl.net.listeners.WeatherDailyListener;
import com.mlsdev.weather.services.net.IWeatherNetworkService;
import com.mlsdev.weather.util.UrlBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public class WeatherNetworkService implements IWeatherNetworkService {

    private BaseWeatherListener listener;
    private WeatherDailyListener dailyListener;

    public WeatherNetworkService(BaseWeatherListener listener) {
        this.listener = listener;
    }

    public WeatherNetworkService(WeatherDailyListener dailyListener) {
        this.dailyListener = dailyListener;
    }

    private Response.Listener<Weather> successGetWeather = new Response.Listener<Weather>() {
        @Override
        public void onResponse(Weather weather) {
            if (weather.getCode() == 200) {
                listener.onSuccessGetWeatherByCity(weather);
            } else if (weather.getCode() == 404) {
                listener.onErrorGetWeatherByCity("City not found");
            } else {
                listener.onErrorGetWeatherByCity("Server Error");
            }
        }
    };

    private Response.ErrorListener errorGetWeather = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            listener.onErrorGetWeatherByCity("error");
        }
    };

    @Override
    public void getWeatherById(int id) {
        String url = UrlBuilder.getWeatherUrlById(id);
        ServerRequest<Weather> request = new ServerRequest<>(Request.Method.POST, url, Weather.class, successGetWeather, errorGetWeather);
        ServerRequest.getRequestQueue().add(request);
    }

    @Override
    public void getWeatherByCity(String cityName) {
        String url = UrlBuilder.getWeatherUrlByCity(cityName);
        ServerRequest<Weather> request = new ServerRequest<>(Request.Method.POST, url, Weather.class, successGetWeather, errorGetWeather);
        ServerRequest.getRequestQueue().add(request);
    }

    private Response.Listener<WeatherList> successGetAllWeather = new Response.Listener<WeatherList>() {
        @Override
        public void onResponse(WeatherList weathers) {
            listener.onSuccessGetAllWeathers(weathers);
        }
    };

    private Response.ErrorListener errorGetAllWeather = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            listener.onErrorGetAllWeathers("error");
        }
    };

    @Override
    public void getWeatherByCitiesName(List<String> citiesName) {
        String url = UrlBuilder.getWeatherUrlByCitiesId(citiesName);
        ServerRequest<WeatherList> request = new ServerRequest<>(Request.Method.POST, url, WeatherList.class, successGetAllWeather, errorGetAllWeather);
        ServerRequest.getRequestQueue().add(request);
    }

    @Override
    public void getWeatherByCitiesId(List<String> citiesIdList) {
        String url = UrlBuilder.getWeatherUrlByCitiesId(citiesIdList);
        ServerRequest<WeatherList> request = new ServerRequest<>(Request.Method.POST, url, WeatherList.class, successGetAllWeather, errorGetAllWeather);
        ServerRequest.getRequestQueue().add(request);
    }

    private Response.Listener<DailyWeatherList> successGetDailyWeather = new Response.Listener<DailyWeatherList>() {
        @Override
        public void onResponse(DailyWeatherList dailyList) {
            dailyListener.onSuccessGetDailyWeather(dailyList);
        }
    };

    private Response.ErrorListener errorGetDailyWeather = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            dailyListener.onErrorGetDailyWeather("error");
        }
    };

    public void getDailyWeather(String cityId, int count, boolean isFirstLoad) {
        String url = UrlBuilder.getDailyWeather(cityId, count);
        ServerRequest<DailyWeatherList> request = null;
        if (isFirstLoad) {
            request = new ServerRequest<>(Request.Method.POST, url, DailyWeatherList.class, successFirstLoadDailyWeather, errorFirstLoadDailyWeather);
        } else {
            request = new ServerRequest<>(Request.Method.POST, url, DailyWeatherList.class, successGetDailyWeather, errorGetDailyWeather);
        }
        ServerRequest.getRequestQueue().add(request);
    }

    private Response.Listener<DailyWeatherList> successFirstLoadDailyWeather = new Response.Listener<DailyWeatherList>() {
        @Override
        public void onResponse(DailyWeatherList dailyList) {
            dailyListener.onSuccessFirstLoadDailyWeather(dailyList);
        }
    };

    private Response.ErrorListener errorFirstLoadDailyWeather = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            dailyListener.onErrorFirstLoadDailyWeather("error");
        }
    };

}
