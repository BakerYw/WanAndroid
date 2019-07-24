package com.nyw.wanandroid.module.web.widget;

import android.content.Context;
import android.view.View;

import com.nyw.wanandroid.R;

import razerdp.basepopup.BasePopupWindow;

public class WebPopup extends BasePopupWindow {
    public WebPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        View view = createPopupById(R.layout.web_popup);
        view.findViewById(R.id.web_pop_collect).setOnClickListener(v -> {
            dismiss();
//            if (UserCacheUtil.checkIsLoginWithJump(null) && UserCacheUtil.checkIsCircleOwnerWithJump()) {
//                Navigation.navigateToCirclePublish(null);
//            }
        });
        view.findViewById(R.id.web_pop_afert).setOnClickListener(v -> {
            dismiss();
//            if (UserCacheUtil.checkIsLoginWithJump(null) && UserCacheUtil.checkIsCircleOwnerWithJump()) {
//                Navigation.navigateToCircleCreate(null);
//            }
        });
        view.findViewById(R.id.web_pop_l).setOnClickListener(v -> {
            dismiss();
//            if (UserCacheUtil.checkIsLoginWithJump(null) && UserCacheUtil.checkIsCircleOwnerWithJump()) {
//                Navigation.navigateToCircleCreate(null);
//            }
        });
        return view;
    }
}
