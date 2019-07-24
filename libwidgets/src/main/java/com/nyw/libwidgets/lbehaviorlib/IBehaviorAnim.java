package com.nyw.libwidgets.lbehaviorlib;


import android.view.animation.Interpolator;

public interface IBehaviorAnim {

    Interpolator getInterpolator();

    void setInterpolator(Interpolator interpolator);

    int getDuration();

    void setDuration(int duration);

    void show();

    void hide();
}
