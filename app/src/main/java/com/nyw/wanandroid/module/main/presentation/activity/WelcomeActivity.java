package com.nyw.wanandroid.module.main.presentation.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.blankj.utilcode.util.BarUtils;
import com.nyw.domain.common.util.cache.WelcomeCacheUtil;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.libproject.common.activity.WanBaseActivity;
import com.nyw.wanandroid.R;

public class WelcomeActivity extends WanBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        } else {
            jump();
        }
    }

    private void jump() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                    .makeCustomAnimation(this, R.anim.activity_fade_in,
                            R.anim.activity_stable_out);
            Navigation.navigateToHome(activityOptionsCompat, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {
                    finish();
                }
            });
//            if (WelcomeCacheUtil.shouldShowGuide()) {
//                Navigation.navigateToWelcomeGuide(activityOptionsCompat, new NavCallback() {
//                    @Override
//                    public void onArrival(Postcard postcard) {
//                        finish();
//                    }
//                });
//            } else {
//                Navigation.navigateToHome(activityOptionsCompat, new NavCallback() {
//                    @Override
//                    public void onArrival(Postcard postcard) {
//                        finish();
//                    }
//                });
//            }
        }, 2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 123) {
            jump();
        }
    }
}
