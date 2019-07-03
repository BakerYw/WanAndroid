package com.nyw.domain.common.util.cache;

import com.blankj.utilcode.util.SPUtils;

/**
 * @author BakerJ
 * @date 2018/3/29
 */
public class SettingCacheUtil {
    private static final String SPNAME_SETTING = "SPNAME_SETTING";
    private static final String SPKEY_SETTING_PUSH = "SPKEY_SETTING_PUSH",
            SPKEY_SETTING_IMG_NO_WIFI = "SPKEY_SETTING_IMG_NO_WIFI";

    public static boolean getPushEnable() {
        return SPUtils.getInstance(SPNAME_SETTING).getBoolean(SPKEY_SETTING_PUSH, true);
    }

    public static void setPushEnable(boolean enable) {
        SPUtils.getInstance(SPNAME_SETTING).put(SPKEY_SETTING_PUSH, enable);
    }

    public static boolean getImgNoWifiEnable() {
        return SPUtils.getInstance(SPNAME_SETTING).getBoolean(SPKEY_SETTING_IMG_NO_WIFI, true);
    }

    public static void setImgNoWifiEnable(boolean enable) {
        SPUtils.getInstance(SPNAME_SETTING).put(SPKEY_SETTING_IMG_NO_WIFI, enable);
    }
}
