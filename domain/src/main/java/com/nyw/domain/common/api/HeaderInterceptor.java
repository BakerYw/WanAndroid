package com.nyw.domain.common.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bakerj.rxretrohttp.exception.ApiException;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.nyw.libthird.location.LocationClient;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @date 2018/3/23
 */

public class HeaderInterceptor implements Interceptor {
    private static final String COOKIE_PREF = "cookies_prefs";
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isConnected()) {
            throw new ApiException(ApiException.NETWORK_NOT_OPEN, "无可用网络");
        }

        Request request = chain.request();
        //String cookie = getCookie(request.url().toString(), request.url().host());
        request = request.newBuilder()
//                .addHeader("regFrom", "1")
//                .addHeader("deviceOs", "1")
//                .addHeader("deviceId", DeviceUtils.getAndroidID())
//                .addHeader("deviceType", DeviceUtils.getModel())
//                .addHeader("version", String.valueOf(AppUtils.getAppVersionCode()))
//                .addHeader("Cookie", cookie)
                .build();
        return chain.proceed(request);
    }

//    private String getCookie(String url, String domain) {
//        SharedPreferences sp = mContext.getSharedPreferences(COOKIE_PREF, Context.MODE_PRIVATE);
//        if (!TextUtils.isEmpty(url) && sp.contains(url) && !TextUtils.isEmpty(sp.getString(url, ""))) {
//            return sp.getString(url, "");
//        }
//        if (!TextUtils.isEmpty(domain) && sp.contains(domain) && !TextUtils.isEmpty(sp.getString(domain, ""))) {
//            return sp.getString(domain, "");
//        }
//        return null;
//    }
}
