package com.nyw.wanandroid.utils.cookie;


import android.content.Context;
import android.util.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieManager implements CookieJar {
    private PersistentCookieStore cookieStore;

    public CookieManager(Context context) {
        cookieStore = new PersistentCookieStore(context);
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies.size() > 0) {
            for (Cookie cookieItem : cookies) {
                Log.d("nyw","cookie:".concat(cookieItem.domain()).concat(" ").concat(cookieItem.name()));
                cookieStore.add(url, cookieItem);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return cookieStore.get(url);
    }
}
