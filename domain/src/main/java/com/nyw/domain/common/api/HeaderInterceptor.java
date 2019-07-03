package com.nyw.domain.common.api;

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
 * @author BakerJ
 * @date 2018/3/23
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isConnected()) {
            throw new ApiException(ApiException.NETWORK_NOT_OPEN, "无可用网络");
        }
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("regFrom", "1")
                .addHeader("deviceOs", "1")
                .addHeader("deviceId", DeviceUtils.getAndroidID())
                .addHeader("deviceType", DeviceUtils.getModel())
                .addHeader("version", String.valueOf(AppUtils.getAppVersionCode()))
                .build();
        return chain.proceed(request);
    }
}
