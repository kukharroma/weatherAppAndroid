package com.mlsdev.weather.ui.util;

/**
 * Created by romakukhar on 16.02.15.
 */
public class ChartUtil {
    private static ChartUtil ourInstance = new ChartUtil();

    public static ChartUtil getInstance() {
        return ourInstance;
    }

    private ChartUtil() {
    }
}
