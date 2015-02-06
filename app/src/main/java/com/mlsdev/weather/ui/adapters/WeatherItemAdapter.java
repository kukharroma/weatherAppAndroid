package com.mlsdev.weather.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlsdev.weather.model.Weather;

import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 03.02.15.
 */
public class WeatherItemAdapter extends BaseAdapter {

    private List<Weather> weatherList;
    private Context context;
    private int resources;

    public WeatherItemAdapter(Context context, List<Weather> weatherList, int resources) {
        this.context = context;
        this.weatherList = weatherList;
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return weatherList.size();
    }

    @Override
    public Weather getItem(int position) {
        return weatherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView ivWeather;
        TextView tvLocation, tvMainTemp, tvMinMaxTemp, tvWeatherTime, tvMainWeather, tvDescriptionWeather;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resources, parent, false);
            ivWeather = (ImageView) convertView.findViewById(R.id.iv_weather);
            tvLocation = (TextView) convertView.findViewById(R.id.tv_location);
            tvMainTemp = (TextView) convertView.findViewById(R.id.tv_main_temp);
            tvMinMaxTemp = (TextView) convertView.findViewById(R.id.tv_min_max_temp);
            tvWeatherTime = (TextView) convertView.findViewById(R.id.tv_weather_time);
            tvMainWeather = (TextView) convertView.findViewById(R.id.tv_main_weather);
            tvDescriptionWeather = (TextView) convertView.findViewById(R.id.tv_description_weather);
            convertView.setTag(new ViewHolder(ivWeather, tvLocation, tvMainTemp, tvMinMaxTemp, tvWeatherTime, tvMainWeather, tvDescriptionWeather));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            ivWeather = viewHolder.ivWeather;
            tvLocation = viewHolder.tvLocation;
            tvMainTemp = viewHolder.tvMainTemp;
            tvMinMaxTemp = viewHolder.tvMinMaxTemp;
            tvWeatherTime = viewHolder.tvWeatherTime;
            tvMainWeather = viewHolder.tvMainWeather;
            tvDescriptionWeather = viewHolder.tvDescriptionWeather;
        }

        Weather weather = weatherList.get(position);
        ivWeather.setImageResource(R.drawable.weather_image);
        tvLocation.setText(weather.getCity() + ", " + weather.getSys().getCountry());
        tvMainTemp.setText(String.valueOf(weather.getTemperature().getTemp()) + context.getString(R.string.degree));
        tvWeatherTime.setText(weather.getFormattedDate());
        tvMinMaxTemp.setText(String.valueOf(weather.getTemperature().getMinTemp())
                + context.getString(R.string.degree)
                + " / " + String.valueOf(weather.getTemperature().getMinTemp())
                + context.getString(R.string.degree));
        tvMainWeather.setText(weather.getFirstWeater().getMain());
        tvDescriptionWeather.setText(weather.getFirstWeater().getDescription());

        return convertView;
    }

    private static class ViewHolder {
        public final ImageView ivWeather;
        public final TextView tvLocation, tvMainTemp, tvMinMaxTemp, tvWeatherTime, tvMainWeather, tvDescriptionWeather;

        private ViewHolder(ImageView ivWeather, TextView tvLocation, TextView tvMainTemp, TextView tvMinMaxTemp, TextView tvWeatherTime, TextView tvMainWeather, TextView tvDescriptionWeather) {
            this.ivWeather = ivWeather;
            this.tvLocation = tvLocation;
            this.tvMainTemp = tvMainTemp;
            this.tvMinMaxTemp = tvMinMaxTemp;
            this.tvWeatherTime = tvWeatherTime;
            this.tvMainWeather = tvMainWeather;
            this.tvDescriptionWeather = tvDescriptionWeather;
        }
    }

    public void updateList(List<Weather> weatherList) {
        this.weatherList = weatherList;
        this.notifyDataSetChanged();
    }
}
