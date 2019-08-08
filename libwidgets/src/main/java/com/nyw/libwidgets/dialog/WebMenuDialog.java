package com.nyw.libwidgets.dialog;

import android.animation.Animator;
import android.view.View;

import androidx.annotation.NonNull;

import com.nyw.libwidgets.R;
import com.nyw.libwidgets.dialog.AnyLayer.Alignment;
import com.nyw.libwidgets.dialog.AnyLayer.AnimatorHelper;
import com.nyw.libwidgets.dialog.AnyLayer.AnyLayer;
import com.nyw.libwidgets.dialog.AnyLayer.DialogLayer;



public class WebMenuDialog {

    public static void show(@NonNull View target, @NonNull OnMenuClickListener listener) {
        AnyLayer.target(target)
                .contentView(R.layout.dialog_web_menu)
                .alignment(Alignment.Direction.VERTICAL, Alignment.Horizontal.ALIGN_RIGHT, Alignment.Vertical.BELOW, false)
                .contentAnimator(new DialogLayer.AnimatorCreator() {
                    @NonNull
                    @Override
                    public Animator createInAnimator(View target) {
                        return AnimatorHelper.createDelayedZoomInAnim(target, 1F, 0F);
                    }

                    @NonNull
                    @Override
                    public Animator createOutAnimator(View target) {
                        return AnimatorHelper.createDelayedZoomOutAnim(target, 1F, 0F);

                    }
                })

                .onClickToDismiss(new DialogLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(DialogLayer dialogLayer, View v) {
                            listener.onCollect();
                    }
                },R.id.dialog_web_menu_tv_collect)
                .onClickToDismiss(new DialogLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(DialogLayer dialogLayer, View v) {
                            listener.onShare();
                    }
                }, R.id.dialog_web_menu_tv_share)
                .onClickToDismiss(new DialogLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(DialogLayer dialogLayer, View v) {
                        listener.onBrowser();
                    }
                },R.id.dialog_web_menu_tv_browser)
                .show();
    }

    public interface OnMenuClickListener{
        void onCollect();
        void onShare();
        void onBrowser();
    }

}
