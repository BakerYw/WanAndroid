package com.nyw.libproject.common.activity;

import android.widget.TextView;

import com.nyw.libproject.R;


public class WanBaseTitleBackActivity extends WanBaseBackActivity {
    protected TextView commonTvTitle;

    @Override
    protected void inflateBaseView() {
        super.inflateBaseView();
        commonTvTitle = findViewById(R.id.common_tv_title);
    }

    public void setTitleTxt(String title) {
        commonTvTitle.setText(title);
    }

    public void setTitleTxt(int resId) {
        commonTvTitle.setText(resId);
    }

    public void setTitleVisibility(int visibility) {
        commonTvTitle.setVisibility(visibility);
    }
}
