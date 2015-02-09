package com.mlsdev.weather.ui.fragment;

import android.view.View;

/**
 * Created by romakukhar on 09.02.15.
 */
public interface BaseFragment {

    public void initComponents(View view);

    public void fillComponents();

    public void showProgressDialog(String tittle, String message);

    public void dismissProgressDialog();
}
