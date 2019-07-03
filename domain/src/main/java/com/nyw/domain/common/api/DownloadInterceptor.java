package com.nyw.domain.common.api;

import com.bakerj.rxretrohttp.exception.ApiException;
import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author BakerJ
 * @date 2018/3/23
 */

public class DownloadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isConnected()) {
            throw new ApiException(ApiException.NETWORK_NOT_OPEN, "无可用网络");
        }
        Request request = chain.request();
//        request = request.newBuilder()
//                .addHeader("regFrom", "1")
//                .addHeader("deviceOs", "1")
//                .addHeader("deviceId", DeviceUtils.getAndroidID())
//                .addHeader("deviceType", DeviceUtils.getModel())
//                .addHeader("token", UserCacheUtil.getToken())
//                .addHeader("userId", UserCacheUtil.getCircleLeader().getUuid())
//                .addHeader("Accept-Encoding", "identity")
//                .build();
        return chain.proceed(request);
    }
}
