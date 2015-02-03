package com.mlsdev.weather.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by romakukhar on 30.01.15.
 */
@DatabaseTable(tableName = "clouds")
public class Clouds {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "cloudiness")
    @SerializedName("all")
    private String cloudiness;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }
}
