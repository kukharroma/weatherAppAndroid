package com.mlsdev.weather.util;

import android.content.Context;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 31.01.15.
 */
public class DBConfig {

    public static String DATABASE_NAME;
    public static int DATABASE_VERSION;

    public static void init(Context context) {
        DATABASE_NAME = context.getString(R.string.db_name);
        DATABASE_VERSION = context.getResources().getInteger(R.integer.db_version);
    }

}
