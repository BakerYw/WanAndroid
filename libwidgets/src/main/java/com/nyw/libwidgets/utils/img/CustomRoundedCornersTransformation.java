package com.nyw.libwidgets.utils.img;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author BakerJ
 * @date 2018/3/27
 */

public class CustomRoundedCornersTransformation extends RoundedCornersTransformation {
    public CustomRoundedCornersTransformation(int radius, int margin) {
        super(radius, margin);
    }

    public CustomRoundedCornersTransformation(int radius, int margin, CornerType cornerType) {
        super(radius, margin, cornerType);
    }

    @Override
    protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool pool, @NonNull
            Bitmap toTransform, int outWidth, int outHeight) {
        return super.transform(context, pool, TransformationUtils.centerCrop(pool, toTransform,
                outWidth, outHeight), outWidth, outHeight);
    }
}
