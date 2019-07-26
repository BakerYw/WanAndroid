package com.nyw.domain.common.util.cache;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;

/**
 * @author BakerJ
 * @date 2018/4/12
 */
public class AppInfoCacheUtil {

    private static final String SPNAME_APP_INFO = "SPNAME_APP_INFO";
    private static final String SPKEY_APP_INFO_CHANNEL = "SPKEY_APP_INFO_CHANNEL";
    private static final String SPKEY_APP_VERSION = "SPKEY_APP_VERSION";

    public static String getAppChannel() {
        return SPUtils.getInstance(SPNAME_APP_INFO).getString(SPKEY_APP_INFO_CHANNEL,
                "wanandroid");
    }

    public static void setAppChannel(String channel) {
        channel = channel == null ? "wanandroid" : channel;
        SPUtils.getInstance(SPNAME_APP_INFO).put(SPKEY_APP_INFO_CHANNEL, channel);
    }

    public static void setAppVersion() {
        SPUtils.getInstance(SPNAME_APP_INFO).put(SPKEY_APP_VERSION, AppUtils.getAppVersionCode());
    }

    public static boolean isNewUpdate() {
        int version = SPUtils.getInstance(SPNAME_APP_INFO).getInt(SPKEY_APP_VERSION, 0);
        return AppUtils.getAppVersionCode() > version;
    }
}
