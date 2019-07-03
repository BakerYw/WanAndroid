package com.nyw.libproject.common.activity;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by BakerJ on 2017/12/19.
 */

public class SchemeFilterActivity extends WanBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        if (uri != null) {
            ARouter.getInstance().build(uri).navigation();
        }
        finish();
    }
}
