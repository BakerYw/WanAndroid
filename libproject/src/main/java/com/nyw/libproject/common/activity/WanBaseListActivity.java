package com.nyw.libproject.common.activity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bakerj.base.loadmore.mvp.LoadMorePresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nyw.domain.common.Constants;
import com.nyw.libproject.R;

/**
 * @author BakerJ
 * @date 2018/5/9
 */
public abstract class WanBaseListActivity<Presenter extends LoadMorePresenter, Adapter
        extends BaseQuickAdapter<Item, ?>, Item> extends com.bakerj.base.activity
        .BaseListActivity<Presenter, Adapter, Item> {
    protected ImageView commonIvBack;
    protected View commonHeaderRoot;
    protected TextView commonTvTitle;

    protected void inflateBaseView() {
        commonIvBack = findViewById(R.id.common_iv_back);
        commonIvBack.setOnClickListener(view -> back());
        commonHeaderRoot = findViewById(R.id.header_root);
        commonTvTitle = findViewById(R.id.common_tv_title);
    }

    public void setBackVisibility(int visibility) {
        commonIvBack.setVisibility(visibility);
    }

    void back() {
        onBackPressed();
    }

    public void setHeaderVisibility(int visibility) {
        commonHeaderRoot.setVisibility(visibility);
    }

    public void setTitleTxt(String title) {
        commonTvTitle.setText(title);
    }

    public void setTitleTxt(int resId) {
        commonTvTitle.setText(resId);
    }

    public void setTitleVisibility(int visibility) {
        commonTvTitle.setVisibility(visibility);
    }

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
        showToast(getResources().getString(resId));
    }

    @Override
    public Object customFunctionCall(Object obj) {
        return null;
    }
}
