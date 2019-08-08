package com.nyw.domain.common.util.cache;


import com.blankj.utilcode.util.SPUtils;


public class SettingCacheUtil {

    private static final String SP_NAME = "setting";
    private static final String KEY_SHOW_IMAGE = "KEY_SHOW_IMAGE";
    private static final String KEY_SHOW_TOP = "KEY_SHOW_TOP";

    private static final String KEY_WEB_SWIPE_BACK_EDGE = "KEY_WEB_SWIPE_BACK_EDGE";
    private static final String KEY_RV_ANIM = "KEY_RV_ANIM";
    private static final String KEY_SEARCH_HISTORY_MAX_COUNT = "KEY_SEARCH_HISTORY_MAX_COUNT";
    private static final String KEY_UPDATE_IGNORE_DURATION = "KEY_UPDATE_IGNORE_DURATION";

    private final SPUtils mSPUtils = SPUtils.getInstance(SP_NAME);

    private static class Holder {
        private static final SettingCacheUtil INSTANCE = new SettingCacheUtil();
    }

    public static SettingCacheUtil getInstance() {
        return Holder.INSTANCE;
    }

    private SettingCacheUtil() {
    }

    public void setShowImage(boolean show) {
        mSPUtils.put(KEY_SHOW_IMAGE, show);
    }

    public boolean isShowImage() {
        return mSPUtils.getBoolean(KEY_SHOW_IMAGE, true);
    }

    public void setShowTop(boolean showTop) {
        mSPUtils.put(KEY_SHOW_TOP, showTop);
    }

    public boolean isShowTop() {
        return mSPUtils.getBoolean(KEY_SHOW_TOP, true);
    }



    public void setWebSwipeBackEdge(boolean webSwipeBackEdge) {
        mSPUtils.put(KEY_WEB_SWIPE_BACK_EDGE, webSwipeBackEdge);
    }

    public boolean isWebSwipeBackEdge() {
        return mSPUtils.getBoolean(KEY_WEB_SWIPE_BACK_EDGE, false);
    }

    public void setRvAnim(int anim) {
        mSPUtils.put(KEY_RV_ANIM, anim);
    }

    public int getRvAnim() {
        return mSPUtils.getInt(KEY_RV_ANIM,0);
    }

    public void setSearchHistoryMaxCount(int count) {
        mSPUtils.put(KEY_SEARCH_HISTORY_MAX_COUNT, count);
    }

    public int getSearchHistoryMaxCount() {
        return mSPUtils.getInt(KEY_SEARCH_HISTORY_MAX_COUNT, 20);
    }

    public void setUpdateIgnoreDuration(long dur) {
        mSPUtils.put(KEY_UPDATE_IGNORE_DURATION, dur);
    }

    public long getUpdateIgnoreDuration() {
        return mSPUtils.getLong(KEY_UPDATE_IGNORE_DURATION, 24 * 60 * 60 * 1000L);
    }
}
