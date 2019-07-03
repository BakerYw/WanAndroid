package com.nyw.libwidgets.citypicker.adapter;


import com.nyw.libwidgets.citypicker.model.City;

public interface InnerListener {
    void dismiss(int position, City data);

    void locate();
}
