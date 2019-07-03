package com.nyw.libproject.common.hybrid.sonic;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.nyw.domain.common.util.cache.CommonCacheUtil;
import com.tencent.sonic.sdk.SonicConstants;
import com.tencent.sonic.sdk.SonicRuntime;
import com.tencent.sonic.sdk.SonicSessionClient;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author BakerJ
 * @date 2018/3/29
 */
public class SonicRuntimeImpl extends SonicRuntime {
    private String userAgent;

    public SonicRuntimeImpl(Context context, String userAgent) {
        super(context);
        this.userAgent = userAgent;
    }

    @Override
    public void log(String tag, int level, String message) {

    }

    @Override
    public String getCookie(String url) {
        CookieManager cookieManager = CookieManager.getInstance();
        return cookieManager.getCookie(url);
    }

    @Override
    public boolean setCookie(String url, List<String> cookies) {
        if (!TextUtils.isEmpty(url) && cookies != null && cookies.size() > 0) {
            CookieManager cookieManager = CookieManager.getInstance();
            for (String cookie : cookies) {
                cookieManager.setCookie(url, cookie);
            }
            return true;
        }
        return false;

    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String getCurrentUserAccount() {
        return null;
    }

    @Override
    public boolean isSonicUrl(String url) {
        return true;
    }

    @Override
    public Object createWebResourceResponse(String mimeType, String encoding, InputStream data,
                                            Map<String, String> headers) {
        WebResourceResponse resourceResponse = new WebResourceResponse(mimeType, encoding, data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resourceResponse.setResponseHeaders(headers);
        }
        return resourceResponse;
    }

    @Override
    public boolean isNetworkValid() {
        return NetworkUtils.isConnected();
    }

    @Override
    public void showToast(CharSequence text, int duration) {

    }

    @Override
    public void postTaskToThread(Runnable task, long delayMillis) {
        Thread thread = new Thread(task, "SonicThread");
        thread.start();
    }

    @Override
    public void notifyError(SonicSessionClient client, String url, int errorCode) {

    }

    @Override
    public File getSonicCacheDir() {
        File file = new File(Utils.getApp().getCacheDir(), CommonCacheUtil.CACHE_DIR + "/Sonic/");
        if (!file.exists() && !file.mkdir()) {
            notifyError(null, file.getPath(), SonicConstants.ERROR_CODE_MAKE_DIR_ERROR);
        }
        return file;
    }

    @Override
    public File getSonicResourceCacheDir() {
        File file = new File(Utils.getApp().getCacheDir(), CommonCacheUtil.CACHE_DIR +
                "/SonicResource/");
        if (!file.exists() && !file.mkdir()) {
            notifyError(null, file.getAbsolutePath(), SonicConstants.ERROR_CODE_MAKE_DIR_ERROR);
        }
        return file;
    }
}
