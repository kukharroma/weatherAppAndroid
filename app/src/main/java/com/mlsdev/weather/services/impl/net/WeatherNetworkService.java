package com.mlsdev.weather.services.impl.net;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.services.impl.ServerRequest;
import com.mlsdev.weather.services.impl.net.listeners.IGetWeatherByCity;
import com.mlsdev.weather.services.net.IWeatherNetworkService;
import com.mlsdev.weather.util.UrlBuilder;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public class WeatherNetworkService implements IWeatherNetworkService {

    private IGetWeatherByCity action;

    public WeatherNetworkService(IGetWeatherByCity action) {
        this.action = action;
    }


    Response.Listener<Weather> successListener = new Response.Listener<Weather>() {
        @Override
        public void onResponse(Weather weather) {
            action.onSuccessGetWeatherByCity(weather);
        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            action.onErrorGetWeatherByCity("error");
        }
    };

    @Override
    public void getWeatherById(int id) {
        String url = UrlBuilder.getWeatherUrlById(id);
        ServerRequest<Weather> request = new ServerRequest<>(Request.Method.POST, url, Weather.class, successListener, errorListener);
        ServerRequest.getRequestQueue().add(request);
    }

    @Override
    public void getWeatherByCities(List<String> citiesList) {
        String url = UrlBuilder.getWeatherUrlByCitiesId(citiesList);
        ServerRequest<Weather> request = new ServerRequest<>(Request.Method.POST, url, Weather.class, null, null);
        ServerRequest.getRequestQueue().add(request);
    }

    @Override
    public void getWeatherByCity(String cityName) {
        String url = UrlBuilder.getWeatherUrlByCity(cityName);
        ServerRequest<Weather> request = new ServerRequest<>(Request.Method.POST, url, Weather.class, successListener, errorListener);
        ServerRequest.getRequestQueue().add(request);
    }
}
