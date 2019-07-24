package com.nyw.libwidgets.lbehaviorlib.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.nyw.libwidgets.lbehaviorlib.IBehavior;
import com.nyw.libwidgets.lbehaviorlib.IBehaviorAnim;
import com.nyw.libwidgets.lbehaviorlib.anim.TitleBehaviorAnim;


public class TitleBehavior extends CommonBehavior implements IBehavior {

    public TitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    public IBehaviorAnim createBehaviorAnim(CoordinatorLayout coordinatorLayout, View child) {
        return new TitleBehaviorAnim(child);
    }
}
