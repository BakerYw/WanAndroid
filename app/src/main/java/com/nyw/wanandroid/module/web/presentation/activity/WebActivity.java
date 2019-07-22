package com.nyw.wanandroid.module.web.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.hybrid.sonic.WanBaseBrowserActivity;
import com.nyw.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.State;

@Route(path = PathConstants.PATH_WEB_COMMON)
public class WebActivity extends WanBaseBrowserActivity {
    @BindView(R.id.wv_common)
    WebView wvCommon;
    @State
    @Autowired
    String url;
    @BindView(R.id.common_iv_iconL)
    ImageView commonIvIconL;

    @Override
    protected void setContentViewLayout() {
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);
        setDarkStatusBar();
        inflateBaseView();
        initView();
    }

    private void initView() {
        commonIvIconL.setVisibility(View.VISIBLE);
        commonIvIconL.setBackgroundResource(R.mipmap.ic_more);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    protected WebView getWebView() {
        return wvCommon;
    }


    @OnClick(R.id.common_iv_iconL)
    public void onViewClicked() {
    }
}
