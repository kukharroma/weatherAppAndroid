package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by romakukhar on 30.01.15.
 */
@DatabaseTable(tableName = "snow")
public class Snow {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "levelOfPrecipitation")
    @SerializedName("3h")
    private double levelOfPrecipitation;
}
