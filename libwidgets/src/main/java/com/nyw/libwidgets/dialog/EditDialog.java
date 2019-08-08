package com.nyw.libwidgets.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.nyw.libwidgets.R;
import com.nyw.libwidgets.dialog.AnyLayer.AnyLayer;
import com.nyw.libwidgets.dialog.AnyLayer.DialogLayer;


/**
 * @author Cuizhen
 * @date 2018/6/21-上午10:00
 */
public class EditDialog {

    private final Context context;
    private CharSequence title;
    private CharSequence msg;
    private CharSequence yesText;
    private boolean singleBtnYes = false;
    private boolean cancelable = true;
    private String mstr;
    private OnEditDialogClickListener callbackYes = null;
    private SimpleCallback<Void> onDismissListener = null;

    public interface OnEditDialogClickListener{
        void onEditDialog(String str);
    }


    public static EditDialog with(Context context) {
        return new EditDialog(context);
    }

    private EditDialog(Context context) {
        this.context = context;
    }

    public EditDialog yesText(CharSequence yesText) {
        this.yesText = yesText;
        return this;
    }

    public EditDialog yesText(@StringRes int yesText) {
        this.yesText = context.getString(yesText);
        return this;
    }


    public EditDialog title(CharSequence title) {
        this.title = title;
        return this;
    }

    public EditDialog title(@StringRes int title) {
        this.title = context.getString(title);
        return this;
    }

    public EditDialog message(CharSequence msg) {
        this.msg = msg;
        return this;
    }

    public EditDialog message(@StringRes int msg) {
        this.msg = context.getString(msg);
        return this;
    }

    public EditDialog singleYesBtn() {
        singleBtnYes = true;
        return this;
    }

    public EditDialog cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public EditDialog onYes(OnEditDialogClickListener callback) {
        callbackYes = callback;
        return this;
    }


    public EditDialog onDismissListener(SimpleCallback<Void> onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public void show() {
        AnyLayer.with(context)
                .contentView(R.layout.anylayer_common_edit)
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
                        EditText tvContent = dialogLayer.getView(R.id.anylayer_common_tip_tv_edit);
                        tvContent.setText(msg);
                        mstr=tvContent.getText().toString();
                    }
                })
                .gravity(Gravity.CENTER)
                .onClickToDismiss(new DialogLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(DialogLayer dialogLayer, View v) {
                        if (callbackYes != null) {
                            callbackYes.onEditDialog(mstr);
                        }
                    }
                },R.id.anylayer_common_tip_tv_yes).show();
    }
}
