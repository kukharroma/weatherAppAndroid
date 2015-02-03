package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by romakukhar on 30.01.15.
 */
@DatabaseTable(tableName = "wind")
public class Wind {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "speed")
    @SerializedName("speed")
    private String speed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
