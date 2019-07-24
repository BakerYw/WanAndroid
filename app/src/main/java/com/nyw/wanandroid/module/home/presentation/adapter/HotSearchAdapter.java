package com.nyw.wanandroid.module.home.presentation.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.domain.bean.response.home.HotkeyBean;
import com.nyw.wanandroid.R;

public class HotSearchAdapter extends BaseQuickAdapter<HotkeyBean, BaseViewHolder>{
    public HotSearchAdapter() {
        super(R.layout.rv_item_search_hotkey);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotkeyBean item) {
        helper.setText(R.id.hot_item_tv, item.getName());
        if (helper.getAdapterPosition() % 2 == 0) {
            helper.setBackgroundRes(R.id.hot_item_tv, R.drawable.hot_higlight);
            helper.setTextColor(R.id.hot_item_tv,
                    mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            helper.setBackgroundRes(R.id.hot_item_tv, R.drawable.hot_gray);
            helper.setTextColor(R.id.hot_item_tv, Color.parseColor("#444444"));
        }
    }
}
