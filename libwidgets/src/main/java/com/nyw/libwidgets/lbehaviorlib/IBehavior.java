package com.nyw.libwidgets.lbehaviorlib;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Desc : behavior 接口
 * Author : lauzy
 * Date : 2018/9/17
 * Email : freedompaladin@gmail.com
 */
public interface IBehavior {

    @NonNull
    IBehaviorAnim createBehaviorAnim(CoordinatorLayout coordinatorLayout, View child);

    void show();

    void hide();

}
