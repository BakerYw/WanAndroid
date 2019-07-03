package com.nyw.libwidgets.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class TouchHandleViewPager extends ViewPager {
    private boolean canTouch = true;

    public TouchHandleViewPager(@NonNull Context context) {
        super(context);
    }

    public TouchHandleViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanTouch(boolean canTouch) {
        this.canTouch = canTouch;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!canTouch) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
