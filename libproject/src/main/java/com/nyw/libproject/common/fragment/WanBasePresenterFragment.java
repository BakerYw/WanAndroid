package com.nyw.libproject.common.fragment;

import com.bakerj.base.BasePresenter;
import com.bakerj.base.BaseView;
import com.bakerj.base.fragment.BasePresenterFragment;

public abstract class WanBasePresenterFragment<T extends BasePresenter> extends BasePresenterFragment<T> implements BaseView {
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
        showToast(getResources().getString(resId));
    }

    @Override
    public Object customFunctionCall(Object obj) {
        return null;
    }
}
