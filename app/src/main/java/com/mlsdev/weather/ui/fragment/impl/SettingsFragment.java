package com.mlsdev.weather.ui.fragment.impl;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by romakukhar on 18.02.15.
 */
public class SettingsFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource();
    }
}
