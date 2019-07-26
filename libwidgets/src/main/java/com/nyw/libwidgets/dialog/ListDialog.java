package com.nyw.libwidgets.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.libwidgets.R;
import com.nyw.libwidgets.dialog.AnyLayer.AnyLayer;
import com.nyw.libwidgets.dialog.AnyLayer.DialogLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CuiZhen
 * @date 2019/5/20
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class ListDialog {

    private final Context context;
    private CharSequence title;
    private CharSequence yesText;
    private CharSequence noText;
    private boolean noBtn = false;
    private boolean singleBtnYes = false;
    private boolean cancelable = true;
    private OnItemSelectedListener listener = null;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter = null;
    private List<String> datas = new ArrayList<>();
    private int currSelectPos = -1;

    public static ListDialog with(Context context) {
        return new ListDialog(context);
    }

    private ListDialog(Context context) {
        this.context = context;
    }

    public ListDialog title(CharSequence title) {
        this.title = title;
        return this;
    }

    public ListDialog title(@StringRes int title) {
        this.title = context.getString(title);
        return this;
    }

    public ListDialog yesText(CharSequence yesText) {
        this.yesText = yesText;
        return this;
    }

    public ListDialog yesText(@StringRes int yesText) {
        this.yesText = context.getString(yesText);
        return this;
    }

    public ListDialog noText(CharSequence noText) {
        this.noText = noText;
        return this;
    }

    public ListDialog noText(@StringRes int noText) {
        this.noText = context.getString(noText);
        return this;
    }

    public ListDialog noBtn() {
        noBtn = true;
        return this;
    }

    public ListDialog singleYesBtn() {
        singleBtnYes = true;
        return this;
    }

    public ListDialog cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public ListDialog datas(List<String> datas) {
        this.datas.addAll(datas);
        return this;
    }

    public ListDialog datas(String... datas) {
        return datas(Arrays.asList(datas));
    }

    public ListDialog currSelectPos(int currSelectPos) {
        this.currSelectPos = currSelectPos;
        return this;
    }

    public ListDialog listener(OnItemSelectedListener listener) {
        this.listener = listener;
        return this;
    }

    public void show() {
        AnyLayer.with(context)
                .contentView(R.layout.anylayer_common_list)
                .backgroundColorRes(R.color.anylayer_common_bg)
                .cancelableOnTouchOutside(cancelable)
                .cancelableOnClickKeyBack(cancelable)
                .bindData(new DialogLayer.DataBinder() {
                    @Override
                    public void bindData(DialogLayer dialogLayer) {
                        LinearLayout llYesNo = dialogLayer.getView(R.id.anylayer_common_list_ll_yes_no);
                        View vLineH = dialogLayer.getView(R.id.anylayer_common_list_v_line_h);
                        if (noBtn) {
                            vLineH.setVisibility(View.GONE);
                            llYesNo.setVisibility(View.GONE);
                        } else {
                            vLineH.setVisibility(View.VISIBLE);
                            llYesNo.setVisibility(View.VISIBLE);
                            TextView tvYes = dialogLayer.getView(R.id.anylayer_common_list_tv_yes);
                            TextView tvNo = dialogLayer.getView(R.id.anylayer_common_list_tv_no);
                            View vLine = dialogLayer.getView(R.id.anylayer_common_list_v_line);
                            if (yesText != null) {
                                tvYes.setText(yesText);
                            } else {
                                tvYes.setText(R.string.anylayer_common_btn_yes);
                            }
                            if (singleBtnYes) {
                                tvNo.setVisibility(View.GONE);
                                vLine.setVisibility(View.GONE);
                            } else {
                                tvNo.setVisibility(View.VISIBLE);
                                vLine.setVisibility(View.VISIBLE);
                                if (noText != null) {
                                    tvNo.setText(noText);
                                } else {
                                    tvNo.setText(R.string.anylayer_common_btn_no);
                                }
                            }
                        }

                        TextView tvTitle = dialogLayer.getView(R.id.anylayer_common_list_tv_title);
                        if (title == null) {
                            tvTitle.setVisibility(View.GONE);
                        } else {
                            tvTitle.setText(title);
                        }

                        RecyclerView rv = dialogLayer.getView(R.id.anylayer_common_list_rv);
                        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
                        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.anylayer_common_list_rv_item) {
                            @Override
                            protected void convert(BaseViewHolder helper, String item) {
                                TextView tvName = helper.getView(R.id.anylayer_common_list_tv_name);
                                if (helper.getAdapterPosition() == currSelectPos) {
                                    tvName.setTextColor(mContext.getResources().getColor(R.color.anylayer_color));
                                } else {
                                    tvName.setTextColor(mContext.getResources().getColor(R.color.anylayer_common_text_title));
                                }
                                tvName.setText(item);
                            }
                        };
                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                currSelectPos = position;
                                adapter.notifyDataSetChanged();
                                if (noBtn) {
                                    if (listener != null) {
                                        listener.onSelect(datas.get(currSelectPos), currSelectPos);
                                    }
                                    dialogLayer.dismiss();
                                }
                            }
                        });
                        rv.setAdapter(mAdapter);
                        mAdapter.setNewData(datas);
                    }
                })
                .gravity(Gravity.CENTER)
                .onClickToDismiss(new DialogLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(DialogLayer dialogLayer, View v) {
                        if (listener != null) {
                            listener.onSelect(datas.get(currSelectPos), currSelectPos);
                        }
                    }
                },R.id.anylayer_common_list_tv_yes)
                .onClickToDismiss(new DialogLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(DialogLayer dialogLayer, View v) {
                    }
                }, R.id.anylayer_common_list_tv_no)
                .show();
    }

    public interface OnItemSelectedListener {
        void onSelect(String data, int pos);
    }
}
