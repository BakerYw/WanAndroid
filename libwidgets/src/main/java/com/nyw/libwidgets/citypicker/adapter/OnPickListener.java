package com.nyw.libwidgets.citypicker.adapter;


import com.nyw.libwidgets.citypicker.model.City;

public interface OnPickListener {
    void onPick(int position, City data);

    void onLocate();

    void onCancel();

    boolean searchResult(String key, City city);
}
