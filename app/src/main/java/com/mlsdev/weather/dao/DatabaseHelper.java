package com.mlsdev.weather.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mlsdev.weather.model.Clouds;
import com.mlsdev.weather.model.Coordinates;
import com.mlsdev.weather.model.Description;
import com.mlsdev.weather.model.Rain;
import com.mlsdev.weather.model.Snow;
import com.mlsdev.weather.model.Sys;
import com.mlsdev.weather.model.Temperature;
import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.model.Wind;

import java.sql.SQLException;

import mlsdev.com.weather.R;


/**
 * Created by romakukhar on 30.01.15.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private String TAG = this.getClass().toString();

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion, R.raw.db_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.d(TAG, "database start creating");
            TableUtils.createTable(connectionSource, Weather.class);
            TableUtils.createTable(connectionSource, Clouds.class);
            TableUtils.createTable(connectionSource, Coordinates.class);
            TableUtils.createTable(connectionSource, Rain.class);
            TableUtils.createTable(connectionSource, Snow.class);
            TableUtils.createTable(connectionSource, Sys.class);
            TableUtils.createTable(connectionSource, Temperature.class);
            TableUtils.createTable(connectionSource, Wind.class);
            TableUtils.createTable(connectionSource, Description.class);
            Log.d(this.getClass().toString(), "database created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.d(TAG, "database start dropping");
            TableUtils.dropTable(connectionSource, Weather.class, true);
            TableUtils.dropTable(connectionSource, Clouds.class, true);
            TableUtils.dropTable(connectionSource, Coordinates.class, true);
            TableUtils.dropTable(connectionSource, Rain.class, true);
            TableUtils.dropTable(connectionSource, Snow.class, true);
            TableUtils.dropTable(connectionSource, Sys.class, true);
            TableUtils.dropTable(connectionSource, Temperature.class, true);
            TableUtils.dropTable(connectionSource, Wind.class, true);
            TableUtils.dropTable(connectionSource, Weather.class, true);
            Log.d(this.getClass().toString(), "database dropped");
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
