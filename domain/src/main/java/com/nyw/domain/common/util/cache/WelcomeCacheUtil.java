package com.nyw.domain.common.util.cache;

import com.blankj.utilcode.util.SPUtils;

/**
 * @author BakerJ
 * @date 2018/4/11
 */
public class WelcomeCacheUtil {
    private static final int CURRENT_GUIDE_VERSION = 2;
    private static final String SPNAME_WELCOME = "SPNAME_WELCOME";
    private static final String SPKEY_WELCOME_GUIDE_VERSION = "SPKEY_WELCOME_GUIDE_VERSION";

    public static void setGuideVersion() {
        SPUtils.getInstance(SPNAME_WELCOME).put(SPKEY_WELCOME_GUIDE_VERSION, CURRENT_GUIDE_VERSION);
    }

    private static int getGuideVersion() {
        return SPUtils.getInstance(SPNAME_WELCOME).getInt(SPKEY_WELCOME_GUIDE_VERSION, 0);
    }

    public static boolean shouldShowGuide() {
        return getGuideVersion() < CURRENT_GUIDE_VERSION;
    }
}
