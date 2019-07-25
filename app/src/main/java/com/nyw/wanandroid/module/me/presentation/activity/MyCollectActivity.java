package com.nyw.wanandroid.module.me.presentation.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bakerj.base.loadmore.mvp.LoadMorePresenter;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.common.util.cache.UserCacheUtil;
import com.nyw.domain.domain.bean.response.home.CollectionBean;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.activity.WanBaseListActivity;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.presentation.widget.CollectView;
import com.nyw.wanandroid.module.me.mvp.MyCollectionContract;
import com.nyw.wanandroid.module.me.mvp.MyCollectionPresenter;
import com.nyw.wanandroid.module.me.presentation.adapter.CollectionAdapter;

import java.util.List;

import butterknife.ButterKnife;

@Route(path = PathConstants.PATH_COLLECT)
public class MyCollectActivity extends WanBaseListActivity<MyCollectionPresenter
        , CollectionAdapter, CollectionBean> implements MyCollectionContract.View{
    private MyCollectionPresenter actionPresenter;

    @Override
    protected void afterInitView() {
        super.afterInitView();
        ButterKnife.bind(this);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        inflateBaseView();
        setTitleTxt("我的收藏");
        refresh();
        actionPresenter = new MyCollectionPresenter(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Navigation.navigateToWeb(mAdapter.getData().get(position).getLink());
            }
        });
        mAdapter.setOnCollectViewClickListener(new CollectionAdapter.OnCollectViewClickListener() {
            @Override
            public void onClick(BaseViewHolder helper, CollectView v, int position) {
                CollectionBean item = mAdapter.getItem(position);
                if (item != null) {
                    mPresenter.UnCollect(item.getId(),item.getOriginId(),position);
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        BarUtils.setStatusBarLightMode(this, false);
    }

    //带有recycleView和refreshLayout的布局
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected MyCollectionPresenter getPresenter() {
        return new MyCollectionPresenter(this);
    }

    @Override
    protected CollectionAdapter getAdapter() {
        return new CollectionAdapter();
    }

    @Override
    public void refreshSucceed(List<CollectionBean> resultList, boolean hasMore) {
        mAdapter.setNewData(resultList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void UnCollectSuccese(int position) {
        mAdapter.remove(position);
        mAdapter.notifyDataSetChanged();
    }
}
