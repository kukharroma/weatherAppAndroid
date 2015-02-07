package com.mlsdev.weather.services.net;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public interface IWeatherNetworkService {

    public void getWeatherByCity(String cityName);

    public void getWeatherByCitiesId(List<String> citiesList);

    public void getWeatherById(int id);

}
