package com.mlsdev.weather.services.net;

import java.util.List;

/**
 * Created by romakukhar on 31.01.15.
 */
public interface IWeatherNetworkService {

    public void getWeatherByCityName(String cityName);

    public void getWeatherByCitiesName(List<String> citiesList);

    public void getWeatherById(int id);

}
