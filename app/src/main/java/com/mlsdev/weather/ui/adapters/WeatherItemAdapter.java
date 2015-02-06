package com.mlsdev.weather.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlsdev.weather.model.Weather;
import com.mlsdev.weather.ui.model.WeatherItem;

import java.util.List;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 03.02.15.
 */
public class WeatherItemAdapter extends BaseAdapter {

    private List<WeatherItem> weatherItemList;
    private Context context;
    private int resources;

    private boolean isShowCheckBox = false;

    public WeatherItemAdapter(Context context, List<WeatherItem> weatherList, int resources) {
        this.context = context;
        this.weatherItemList = weatherList;
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return weatherItemList.size();
    }

    @Override
    public WeatherItem getItem(int position) {
        return weatherItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView ivWeather;
        TextView tvLocation, tvMainTemp, tvMinMaxTemp, tvWeatherTime, tvMainWeather, tvDescriptionWeather;
        final CheckBox cbDelete;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resources, parent, false);
            ivWeather = (ImageView) convertView.findViewById(R.id.iv_weather);
            tvLocation = (TextView) convertView.findViewById(R.id.tv_location);
            tvMainTemp = (TextView) convertView.findViewById(R.id.tv_main_temp);
            tvMinMaxTemp = (TextView) convertView.findViewById(R.id.tv_min_max_temp);
            tvWeatherTime = (TextView) convertView.findViewById(R.id.tv_weather_time);
            tvMainWeather = (TextView) convertView.findViewById(R.id.tv_main_weather);
            tvDescriptionWeather = (TextView) convertView.findViewById(R.id.tv_description_weather);
            cbDelete = (CheckBox) convertView.findViewById(R.id.cb_delete_weather);
            convertView.setTag(new ViewHolder(ivWeather, tvLocation, tvMainTemp, tvMinMaxTemp, tvWeatherTime, tvMainWeather, tvDescriptionWeather, cbDelete));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            ivWeather = viewHolder.ivWeather;
            tvLocation = viewHolder.tvLocation;
            tvMainTemp = viewHolder.tvMainTemp;
            tvMinMaxTemp = viewHolder.tvMinMaxTemp;
            tvWeatherTime = viewHolder.tvWeatherTime;
            tvMainWeather = viewHolder.tvMainWeather;
            tvDescriptionWeather = viewHolder.tvDescriptionWeather;
            cbDelete = viewHolder.cbDelete;
        }

        WeatherItem item = weatherItemList.get(position);
        ivWeather.setImageResource(R.drawable.weather_image);
        tvLocation.setText(item.getWeather().getCity() + ", " + item.getWeather().getSys().getCountry());
        tvMainTemp.setText(String.valueOf(item.getWeather().getTemperature().getTemp()) + context.getString(R.string.degree));
        tvWeatherTime.setText(item.getWeather().getFormattedDate());
        tvMinMaxTemp.setText(String.valueOf(item.getWeather().getTemperature().getMinTemp()) + context.getString(R.string.degree) + " / " + String.valueOf(item.getWeather().getTemperature().getMinTemp()) + context.getString(R.string.degree));
        tvMainWeather.setText(item.getWeather().getFirstWeater().getMain());
        tvDescriptionWeather.setText(item.getWeather().getFirstWeater().getDescription());

        if (isShowCheckBox) {
            cbDelete.setVisibility(View.VISIBLE);
        } else {
            cbDelete.setVisibility(View.GONE);
        }

        return convertView;
    }

    private static class ViewHolder {
        public final ImageView ivWeather;
        public final TextView tvLocation, tvMainTemp, tvMinMaxTemp, tvWeatherTime, tvMainWeather, tvDescriptionWeather;
        public final CheckBox cbDelete;

        private ViewHolder(ImageView ivWeather, TextView tvLocation, TextView tvMainTemp, TextView tvMinMaxTemp, TextView tvWeatherTime, TextView tvMainWeather, TextView tvDescriptionWeather, CheckBox cbDelete) {
            this.ivWeather = ivWeather;
            this.tvLocation = tvLocation;
            this.tvMainTemp = tvMainTemp;
            this.tvMinMaxTemp = tvMinMaxTemp;
            this.tvWeatherTime = tvWeatherTime;
            this.tvMainWeather = tvMainWeather;
            this.tvDescriptionWeather = tvDescriptionWeather;
            this.cbDelete = cbDelete;
        }
    }

    public void updateList(List<WeatherItem> weatherList) {
        this.weatherItemList = weatherList;
        this.notifyDataSetChanged();
    }

    public void showDeletingCheckBox() {
        isShowCheckBox = !isShowCheckBox;
        this.notifyDataSetChanged();
    }


}
