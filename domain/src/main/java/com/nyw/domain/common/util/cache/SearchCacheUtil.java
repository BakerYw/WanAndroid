package com.nyw.domain.common.util.cache;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class SearchCacheUtil {

    private static final String SP_NAME = "search_history";
    private static final String KEY_HISTORY = "KEY_HISTORY";

    private final SPUtils mSPUtils = SPUtils.getInstance();
    private final Gson mGson = new Gson();

    public static SearchCacheUtil newInstance() {
        return new SearchCacheUtil();
    }

    private SearchCacheUtil() {
    }

    public void save(List<String> historys) {
        if (historys == null || historys.isEmpty()) {
            mSPUtils.clear();
            return;
        }
        String json = mGson.toJson(historys);
        mSPUtils.put(KEY_HISTORY, json);
    }

    public List<String> get() {
        String json = mSPUtils.getString(KEY_HISTORY, "");
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mGson.fromJson(json, new TypeToken<List<String>>(){}.getType());
        } catch (Exception e){
            mSPUtils.clear();
            return null;
        }
    }

    public void clearHistorySearch() {
        mSPUtils.put(KEY_HISTORY, "");
    }
}
