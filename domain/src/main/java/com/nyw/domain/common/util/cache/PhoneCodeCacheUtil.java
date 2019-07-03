package com.nyw.domain.common.util.cache;

import com.blankj.utilcode.util.SPUtils;

/**
 * @author BakerJ
 * @date 2018/4/9
 */
public class PhoneCodeCacheUtil {
    private static final String SPNAME_PHONE_CODE = "SPNAME_PHONE_CODE";
    private static final String SPKEY_LAST_REQUEST_TIME = "SPKEY_LAST_REQUEST_TIME";
    private static final String SPKEY_LAST_PHONE = "SPKEY_LAST_PHONE";

    public static void resetCodeFreezeTime() {
        SPUtils.getInstance(SPNAME_PHONE_CODE).put(SPKEY_LAST_REQUEST_TIME, System
                .currentTimeMillis());
    }

    public static void clearCodeFreeze() {
        SPUtils.getInstance(SPNAME_PHONE_CODE).put(SPKEY_LAST_REQUEST_TIME, 0L);
    }

    public static int getCodeFreezeSecond() {
        return (int) ((System.currentTimeMillis() - SPUtils.getInstance(SPNAME_PHONE_CODE).getLong
                (SPKEY_LAST_REQUEST_TIME, 0L)) / 1000);
    }

    public static String getLastPhone() {
        return SPUtils.getInstance(SPNAME_PHONE_CODE).getString(SPKEY_LAST_PHONE, "");
    }

    public static void setLastPhone(String phone) {
        SPUtils.getInstance(SPNAME_PHONE_CODE).put(SPKEY_LAST_PHONE, phone);
    }

    public static void clearLastPhone() {
        SPUtils.getInstance(SPNAME_PHONE_CODE).put(SPKEY_LAST_PHONE, "");
    }
}
