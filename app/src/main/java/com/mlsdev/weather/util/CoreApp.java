package com.mlsdev.weather.util;

import android.content.Context;

import com.mlsdev.weather.dao.DaoManager;
import com.mlsdev.weather.services.impl.ServerRequest;
import com.mlsdev.weather.services.impl.ServiceManager;

/**
 * Created by romakukhar on 31.01.15.
 */
public class CoreApp {

    public static void init(Context context) {
        DBConfig.init(context);
        PrefManager.init(context);
        DaoManager.init(context);
        ServerRequest.init(context);
        ServiceManager.init(context);
    }

}
