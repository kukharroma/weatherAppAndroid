package com.mlsdev.weather.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by romakukhar on 11.02.15.
 */
public class DateUtil {


    public static String getDayName(String dateStr) {
        Date todayDate = new Date(Long.parseLong(dateStr) * 1000);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
        return dayFormat.format(todayDate);
    }

}
