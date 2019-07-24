package com.nyw.wanandroid.module.wechat.presentation.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBaseListPresenterFragment;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.presentation.adapter.HomeAdapter;
import com.nyw.wanandroid.module.wechat.mvp.wechatListContract;
import com.nyw.wanandroid.module.wechat.mvp.wechatListPresenter;

import java.util.List;

import icepick.State;

@Route(path = PathConstants.PATH_WECHAT_LIST)
public class WechatListFragment extends WanBaseListPresenterFragment<wechatListPresenter, HomeAdapter,
        ArticleBean> implements wechatListContract.View {
    @Autowired
    @State
    int subcat;

    @Override
    protected HomeAdapter getAdapter() {
        return new HomeAdapter();
    }

    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        mRefreshLayout.setBackgroundColor(getResources().getColor(R.color.bg_gray));
        setPresenter(new wechatListPresenter(this, subcat));
    }

    @Override
    protected void afterInitView() {
        super.afterInitView();
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(true);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Navigation.navigateToWeb(mAdapter.getData().get(position).getLink());
            }
        });
    }

    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
        refresh();
    }

    @Override
    public void refreshSucceed(List<ArticleBean> resultList, boolean hasMore) {
        mAdapter.setNewData(resultList);
        mAdapter.notifyDataSetChanged();
        super.refreshSucceed(resultList, hasMore);
    }
}
