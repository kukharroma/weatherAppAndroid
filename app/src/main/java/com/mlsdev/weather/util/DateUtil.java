package com.mlsdev.weather.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by romakukhar on 11.02.15.
 */
public class DateUtil {


    public static String getDayName(String dateStr) {
        Date todayDate = new Date(dateStr);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        return dayFormat.format(todayDate);
    }
}
