package com.nyw.libwidgets.utils.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

/**
 * @author BakerJ
 * @date 2018/5/21
 */
public class GlideUtil {
    public static RequestBuilder<Drawable> load(Context context, String url, int height, int
            width) {
        return Glide.with(context).load(castUrl(url, height, width));
    }

    public static RequestBuilder<Drawable> load(View view, String url, int height, int
            width) {
        return Glide.with(view).load(castUrl(url, height, width));
    }

    private static String castUrl(String url, int height, int width) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        if (url.startsWith("http://img.mamayy.com/v1/tfs") && (url.endsWith(".jpg") || url
                .endsWith(".png")) && height > 0 && width > 0) {
            float scale = ScreenUtils.getScreenDensityDpi() / 3 * 1.0f / 160;
            url = new StringBuilder(url).insert(url.indexOf("tfs") + 3,
                    "_" + (int) (height * scale) + "_" +
                            (int) (width * scale)).toString();
        }
        return url;
    }
}
