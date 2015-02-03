package com.mlsdev.weather.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 03.02.15.
 */
public class MainLoadFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_load_layout, container, false);
        return view;
    }
}
