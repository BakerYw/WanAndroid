package com.nyw.wanandroid.module.knowledge.presentation.fragment;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.common.util.cache.SettingCacheUtil;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.domain.domain.event.home.CollectionEvent;
import com.nyw.domain.domain.event.setting.SettingChangeEvent;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBaseListPresenterFragment;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.mvp.HomePresenter;
import com.nyw.wanandroid.module.home.presentation.adapter.HomeAdapter;
import com.nyw.wanandroid.module.home.presentation.widget.CollectView;
import com.nyw.wanandroid.module.knowledge.mvp.KnowDetailContract;
import com.nyw.wanandroid.module.knowledge.mvp.KnowDetailPresenter;
import com.nyw.wanandroid.utils.RvAnimUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        RvAnimUtils.setAnim(mAdapter, SettingCacheUtil.getInstance().getRvAnim());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Navigation.navigateToWeb(mAdapter.getData().get(position).getLink(),mAdapter.getData().get(position).getId());
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
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSettingChangeEvent(SettingChangeEvent event) {
        if (isDetached()) {
            return;
        }
        if (event.isRvAnimChanged()) {
            RvAnimUtils.setAnim(mAdapter, SettingCacheUtil.getInstance().getRvAnim());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionEvent(CollectionEvent event) {
        if (isDetached()) {
            return;
        }
        if (event.getArticleId() == -1) {
            return;
        }
        List<ArticleBean> list = mAdapter.getData();
        for (int i = 0; i < list.size(); i++) {
            ArticleBean item = list.get(i);
            if (item.getId() == event.getArticleId()) {
                if (item.isCollect() != event.isCollect()) {
                    item.setCollect(event.isCollect());
                    mAdapter.notifyItemChanged(i + mAdapter.getHeaderLayoutCount());
                }
                break;
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
