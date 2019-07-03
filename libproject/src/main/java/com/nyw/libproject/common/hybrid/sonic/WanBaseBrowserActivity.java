package com.nyw.libproject.common.hybrid.sonic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.nyw.libproject.common.activity.WanBaseTitleBackActivity;


/**
 * @author BakerJ
 * @date 2018/3/29
 */
public abstract class WanBaseBrowserActivity extends WanBaseTitleBackActivity implements IBrowser {
    protected BrowserDelegate mBrowserDelegate = new BrowserDelegate();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // start init flow ...
        // in the real world, the init flow may cost a long time as startup
        // runtime、init configs....
        setContentViewLayout();

        // init webview
        WebView webView = getWebView();
        mBrowserDelegate.onCreate(this, webView, getUrl());
    }

    public abstract String getUrl();

    @Override
    public void onPageLoadFinish() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        mBrowserDelegate.onDestroy(getWebView());
        super.onDestroy();
    }


    protected abstract void setContentViewLayout();

    protected abstract WebView getWebView();
}
