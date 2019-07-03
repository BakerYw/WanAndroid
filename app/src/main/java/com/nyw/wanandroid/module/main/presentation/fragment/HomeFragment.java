package com.nyw.wanandroid.module.main.presentation.fragment;

import android.widget.ImageView;

import androidx.recyclerview.widget.SimpleItemAnimator;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.CBBaseListPresenterFragment;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.main.mvp.homeContract;
import com.nyw.wanandroid.module.main.mvp.homePresenter;
import com.nyw.wanandroid.module.main.presentation.adapter.homeAdapter;

import java.util.List;

import butterknife.BindView;

@Route(path = PathConstants.PATH_CIRCLE)
public class HomeFragment extends CBBaseListPresenterFragment<homePresenter, homeAdapter,
        ArticleBean> implements homeContract.View {
    @BindView(R.id.circle_btn_add)
    ImageView circleBtnAdd;
    private int page = 1;

    //带有recycleView和refreshLayout的布局
    @Override
    protected int getLayoutId() {
        return R.layout.frag_main;
    }
    @Override
    protected void beforeInitView() {
        super.beforeInitView();
    }

    @Override
    protected void afterInitView() {
        super.afterInitView();
        ((SimpleItemAnimator) mRecycleView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    @Override
    public void refreshSucceed(List<ArticleBean> resultList, boolean hasMore) {
        mAdapter.addData(0, resultList);
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.finishRefresh(0, true, !hasMore);
    }

    //分页Presenter初始化
    @Override
    public homeContract.Presenter getPresenter() {
        return new homePresenter(this, page);
    }

    @Override
    protected homeAdapter getAdapter() {
        return new homeAdapter();
    }

}
