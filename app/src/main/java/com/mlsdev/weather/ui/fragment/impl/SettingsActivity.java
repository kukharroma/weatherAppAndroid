package com.mlsdev.weather.ui.fragment.impl;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import mlsdev.com.weather.R;

/**
 * Created by romakukhar on 18.02.15.
 * //
 */
public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment(), "").commit();
    }

    class SettingsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }
    }
}


