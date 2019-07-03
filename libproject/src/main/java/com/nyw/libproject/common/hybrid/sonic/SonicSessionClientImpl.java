package com.nyw.libproject.common.hybrid.sonic;

import android.os.Bundle;
import android.webkit.WebView;

import com.tencent.sonic.sdk.SonicSessionClient;

import java.util.HashMap;

/**
 * @author BakerJ
 * @date 2018/3/29
 */
public class SonicSessionClientImpl extends SonicSessionClient {
    private WebView webView;

    public void bindWebView(WebView webView) {
        this.webView = webView;
    }

    public WebView getWebView() {
        return webView;
    }

    @Override
    public void loadUrl(String url, Bundle extraData) {
        webView.loadUrl(url);
    }

    @Override
    public void loadDataWithBaseUrl(String baseUrl, String data, String mimeType, String
            encoding, String historyUrl) {
        webView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }


    @Override
    public void loadDataWithBaseUrlAndHeader(String baseUrl, String data, String mimeType, String
            encoding, String historyUrl, HashMap<String, String> headers) {
        loadDataWithBaseUrl(baseUrl, data, mimeType, encoding, historyUrl);
    }

    public void destroy() {
        if (null != webView) {
            webView.destroy();
            webView = null;
        }
    }
}
