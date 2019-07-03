package com.nyw.libthird.sharelib;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

/**
 * @author BakerJ
 * @date 2017/12/29
 */

public class ShareInit {
    public static void init(Application application, String channel) {
        UMConfigure.init(application, "5a45d85ca40fa35837000354", channel, UMConfigure
                .DEVICE_TYPE_PHONE, "");
        PlatformConfig.setWeixin("wx0f25933afc3cc9a4", "d3737c5eb326c0a8b8c3a2592884ccdb");
        PlatformConfig.setQQZone("1105704078", "c0qFQmuqrYB9wB01");
//        PlatformConfig.setAlipay("");
        UMShareAPI umShareAPI = UMShareAPI.get(application);
        umShareAPI.setShareConfig(new UMShareConfig().isNeedAuthOnGetUserInfo(true));
    }
}
