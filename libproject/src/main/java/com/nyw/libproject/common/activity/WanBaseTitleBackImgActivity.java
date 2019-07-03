package com.nyw.libproject.common.activity;

import android.view.View;
import android.widget.ImageView;

import com.nyw.libproject.R;


/**
 * @author BakerJ
 * @date 2017/12/30
 */

public class WanBaseTitleBackImgActivity extends WanBaseTitleBackActivity {
    ImageView commonIvIconL;
    ImageView commonIvIconR;

    @Override
    protected void inflateBaseView() {
        super.inflateBaseView();
        commonIvIconL = findViewById(R.id.common_iv_iconL);
        commonIvIconR = findViewById(R.id.common_iv_iconR);
    }

    protected void setRightImg(int resId) {
        commonIvIconR.setVisibility(View.VISIBLE);
        commonIvIconR.setImageResource(resId);
        commonIvIconR.setOnClickListener(view -> {
            onRightImgClick();
        });
    }

    protected void onRightImgClick() {

    }

    protected void setLeftImg(int resId) {
        commonIvIconL.setVisibility(View.VISIBLE);
        commonIvIconL.setImageResource(resId);
        commonIvIconL.setOnClickListener(view -> {
            onLeftImgClick();
        });
    }

    protected void onLeftImgClick() {

    }
}
