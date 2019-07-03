package com.nyw.wanandroid.module.main.presentation.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.wanandroid.R;


public class homeAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder>{


    public homeAdapter() {
        super(R.layout.adapter_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.text,item.getChapterName());
    }
}
