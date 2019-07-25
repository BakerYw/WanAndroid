package com.nyw.wanandroid.module.knowledge.presentation.fragment;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBaseListPresenterFragment;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.presentation.adapter.HomeAdapter;
import com.nyw.wanandroid.module.home.presentation.widget.CollectView;
import com.nyw.wanandroid.module.knowledge.mvp.KnowDetailContract;
import com.nyw.wanandroid.module.knowledge.mvp.KnowDetailPresenter;

import java.util.List;

import icepick.State;


@Route(path = PathConstants.PATH_KNOWLEDG_DETAIL)
public class KnowledgeArtDetailFragment extends WanBaseListPresenterFragment<KnowDetailPresenter, HomeAdapter,
        ArticleBean> implements KnowDetailContract.View{
    @State
    @Autowired
    int cid;
    @Override
    protected void afterInitView() {
        super.afterInitView();
        mRefreshLayout.setBackgroundColor(getResources().getColor(R.color.bg_gray));
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(true);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Navigation.navigateToWeb(mAdapter.getData().get(position).getLink());
            }
        });
        mAdapter.setOnCollectViewClickListener(new HomeAdapter.OnCollectViewClickListener() {
            @Override
            public void onClick(BaseViewHolder helper, CollectView v, int position) {
                if (!v.isChecked()) {
                    mPresenter.Collect(mAdapter.getData().get(position).getId());
                } else {
                    mPresenter.UnCollect(mAdapter.getData().get(position).getId());
                }
            }
        });
    }
    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        setPresenter(new KnowDetailPresenter(this,cid));
    }
    @Override
    protected HomeAdapter getAdapter() {
        return new HomeAdapter();
    }
    @Override
    public void refreshSucceed(List<ArticleBean> resultList, boolean hasMore) {
        mAdapter.setNewData(resultList);
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.finishRefresh(0, true, !hasMore);
    }
    @Override
    public void CollectSuccess() {
        ToastUtils.showShort("收藏成功");
    }

    @Override
    public void UnCollectSuccess() {
        ToastUtils.showShort("取消收藏");
    }
    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
        refresh();
    }
}
