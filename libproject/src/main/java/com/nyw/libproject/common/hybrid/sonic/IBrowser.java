package com.nyw.libproject.common.hybrid.sonic;

import android.app.Activity;

/**
 * @author BakerJ
 * @date 2018/5/19
 */
public interface IBrowser {
    Activity getActivity();

    void onPageLoadFinish();
}
