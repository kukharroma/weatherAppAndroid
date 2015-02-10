package com.mlsdev.weather.services.impl.net;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.model.WeatherList;
import com.mlsdev.weather.services.impl.ServerRequest;
import com.mlsdev.weather.services.impl.net.listeners.BaseWeatherListener;
import com.mlsdev.weather.services.net.IWeatherNetworkService;
import com.mlsdev.weather.util.UrlBuilder;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public class WeatherNetworkService implements IWeatherNetworkService {

    private BaseWeatherListener listener;

    public WeatherNetworkService(BaseWeatherListener listener) {
        this.listener = listener;
    }

    Response.Listener<Weather> successGetWeather = new Response.Listener<Weather>() {
        @Override
        public void onResponse(Weather weather) {
            listener.onSuccessGetWeatherByCity(weather);
        }
    };

    Response.ErrorListener errorGetWeather = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            listener.onErrorGetWeatherByCity("error");
        }
    };

    Response.Listener<WeatherList> successGetAllWeather = new Response.Listener<WeatherList>() {
        @Override
        public void onResponse(WeatherList weathers) {
            listener.onSuccessGetAllWeathers(weathers);
        }
    };

    Response.ErrorListener errorGetAllWeather = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            listener.onErrorGetAllWeathers("error");
        }
    };

    @Override
    public void getWeatherById(int id) {
        String url = UrlBuilder.getWeatherUrlById(id);
        ServerRequest<Weather> request = new ServerRequest<>(Request.Method.POST, url, Weather.class, successGetWeather, errorGetWeather);
        ServerRequest.getRequestQueue().add(request);
    }

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

    @Override
    public void getWeatherByCity(String cityName) {
        String url = UrlBuilder.getWeatherUrlByCity(cityName);
        ServerRequest<Weather> request = new ServerRequest<>(Request.Method.POST, url, Weather.class, successGetWeather, errorGetWeather);
        ServerRequest.getRequestQueue().add(request);
    }
}
