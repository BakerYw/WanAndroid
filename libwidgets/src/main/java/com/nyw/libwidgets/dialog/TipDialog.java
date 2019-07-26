package com.nyw.libwidgets.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.nyw.libwidgets.dialog.AnyLayer.AnyLayer;
import com.nyw.libwidgets.dialog.AnyLayer.DialogLayer;
import com.nyw.libwidgets.R;



/**
 * @author Cuizhen
 * @date 2018/6/21-上午10:00
 */
public class TipDialog {

    private final Context context;
    private CharSequence title;
    private CharSequence msg;
    private CharSequence yesText;
    private CharSequence noText;
    private boolean singleBtnYes = false;
    private boolean cancelable = true;
    private SimpleCallback<Void> callbackYes = null;
    private SimpleCallback<Void> callbackNo = null;
    private SimpleCallback<Void> onDismissListener = null;

    public static TipDialog with(Context context) {
        return new TipDialog(context);
    }

    private TipDialog(Context context) {
        this.context = context;
    }

    public TipDialog yesText(CharSequence yesText) {
        this.yesText = yesText;
        return this;
    }

    public TipDialog yesText(@StringRes int yesText) {
        this.yesText = context.getString(yesText);
        return this;
    }

    public TipDialog noText(CharSequence noText) {
        this.noText = noText;
        return this;
    }

    public TipDialog noText(@StringRes int noText) {
        this.noText = context.getString(noText);
        return this;
    }

    public TipDialog title(CharSequence title) {
        this.title = title;
        return this;
    }

    public TipDialog title(@StringRes int title) {
        this.title = context.getString(title);
        return this;
    }

    public TipDialog message(CharSequence msg) {
        this.msg = msg;
        return this;
    }

    public TipDialog message(@StringRes int msg) {
        this.msg = context.getString(msg);
        return this;
    }

    public TipDialog singleYesBtn() {
        singleBtnYes = true;
        return this;
    }

    public TipDialog cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public TipDialog onYes(SimpleCallback<Void> callback) {
        callbackYes = callback;
        return this;
    }

    public TipDialog onNo(SimpleCallback<Void> callback) {
        callbackNo = callback;
        return this;
    }

    public TipDialog onDismissListener(SimpleCallback<Void> onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public void show() {
        AnyLayer.with(context)
                .contentView(R.layout.anylayer_common_tip)
                .backgroundColorRes(R.color.anylayer_common_bg)
                .cancelableOnTouchOutside(cancelable)
                .cancelableOnClickKeyBack(cancelable)
                .onVisibleChangeListener(new DialogLayer.OnVisibleChangeListener() {
                    @Override
                    public void onShow(DialogLayer dialogLayer) {

                    }

                    @Override
                    public void onDismiss(DialogLayer dialogLayer) {
                        if (onDismissListener != null) {
                            onDismissListener.onResult(null);
                        }
                    }
                })
                .bindData(new DialogLayer.DataBinder() {
                    @Override
                    public void bindData(DialogLayer dialogLayer) {
                        TextView tvYes = dialogLayer.getView(R.id.anylayer_common_tip_tv_yes);
                        TextView tvNo = dialogLayer.getView(R.id.anylayer_common_tip_tv_no);
                        View vLine = dialogLayer.getView(R.id.anylayer_common_tip_v_line);
                        if (singleBtnYes) {
                            tvNo.setVisibility(View.GONE);
                            vLine.setVisibility(View.GONE);
                        } else {
                            if (noText != null) {
                                tvNo.setText(noText);
                            } else {
                                tvNo.setText(R.string.anylayer_common_btn_no);
                            }
                        }

                        if (yesText != null) {
                            tvYes.setText(yesText);
                        } else {
                            tvYes.setText(R.string.anylayer_common_btn_yes);
                        }

                        TextView tvTitle = dialogLayer.getView(R.id.anylayer_common_tip_tv_title);
                        if (title == null) {
                            tvTitle.setVisibility(View.GONE);
                        } else {
                            tvTitle.setText(title);
                        }

                        TextView tvContent = dialogLayer.getView(R.id.anylayer_common_tip_tv_content);
                        tvContent.setText(msg);
                    }
                })
                .gravity(Gravity.CENTER)
                .onClickToDismiss(new DialogLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(DialogLayer dialogLayer, View v) {
                        if (callbackYes != null) {
                            callbackYes.onResult(null);
                        }
                    }
                },R.id.anylayer_common_tip_tv_yes)

                .onClickToDismiss(new DialogLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(DialogLayer dialogLayer, View v) {
                        if (callbackNo != null) {
                            callbackNo.onResult(null);
                        }
                    }
                }, R.id.anylayer_common_tip_tv_no)
                .show();
    }
}
