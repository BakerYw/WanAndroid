package com.nyw.libproject.common.activity;

import android.content.Context;

import com.bakerj.base.BaseView;
import com.nyw.domain.common.Constants;
import com.nyw.libproject.R;

/**
 * @author BakerJ
 * @date 2018/3/23
 */

public class WanBaseActivity extends com.bakerj.base.activity.BaseActivity implements BaseView {
    @Override
    protected String getJumpBundleString() {
        return Constants.JUMP_BUNDLE;
    }

    @Override
    protected int getLayoutLoading() {
        return R.layout.common_layout_loading;
    }

    @Override
    protected int getLoadingStyle() {
        return R.style.CustomDialogStyle;
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading(String msg) {
        showLoading();
    }

    @Override
    public void showLoading(int resId) {
        showLoading();
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    @Override
    public Object customFunctionCall(Object obj) {
        return null;
    }
}
