package com.nyw.libwidgets.utils.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

/**
 * @author NYW
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
/**
 * Glide.with(imageView.getContext())
 *         .asBitmap() // 不显示gif图
 *         .load(imageUrl)
 *         .transition(DrawableTransitionOptions.withCrossFade(500)) // 渐变
 *         .placeholder(R.drawable.ic_avatar_default)// 加载中图片
 *         .error(R.drawable.ic_avatar_default) // 加载失败图片
 *         .transform(new CircleCrop()) // 圆形图片
 *         .transform(new RoundedCorners(20)) // 圆角图片
 *         .transform(new BlurTransformation(50, 8)) // 高斯模糊，参数1：模糊度；参数2：图片缩放x倍后再进行模糊
 *         .listener(new RequestListener<Drawable>() { // 加载监听
 *                         @Override
 *                         public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
 *                             return false;
 *                         }
 *
 *                         @Override
 *                         public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
 *                             return false;
 *                         }
 *                     }).into(imageView);
 */