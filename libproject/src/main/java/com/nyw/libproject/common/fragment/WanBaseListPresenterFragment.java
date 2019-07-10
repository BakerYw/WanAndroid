package com.nyw.libproject.common.fragment;

import android.view.View;

import com.bakerj.base.fragment.BasePresenterListFragment;
import com.bakerj.base.loadmore.mvp.LoadMorePresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class WanBaseListPresenterFragment<Presenter extends LoadMorePresenter, Adapter
        extends BaseQuickAdapter<Item, ?>, Item> extends BasePresenterListFragment<Presenter,
        Adapter, Item> {
    private Unbinder unbinder;

    @Override
    protected void initView(View view) {
        super.initView(view);
        this.unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        if (getContext() != null)
            showToast(getContext().getString(resId));
    }

    @Override
    public Object customFunctionCall(Object obj) {
        return null;
    }
}
