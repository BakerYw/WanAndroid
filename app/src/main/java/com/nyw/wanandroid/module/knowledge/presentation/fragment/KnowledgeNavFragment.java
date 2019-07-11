package com.nyw.wanandroid.module.knowledge.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bakerj.base.widgets.refresh.CustomRefreshLayout;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nyw.domain.domain.bean.response.home.KnowledgeNavBean;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBasePresenterFragment;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.knowledge.mvp.knowledgeNaviContract;
import com.nyw.wanandroid.module.knowledge.mvp.knowledgeNaviPresenter;
import com.nyw.wanandroid.module.knowledge.presentation.adapter.KnowledgeNavAdapter1;
import com.nyw.wanandroid.module.knowledge.presentation.adapter.KnowledgeNavAdapter2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = PathConstants.PATH_KNOWLEDG_NAV)
public class KnowledgeNavFragment extends WanBasePresenterFragment<knowledgeNaviPresenter> implements knowledgeNaviContract.View {
    @BindView(R.id.refresh_layout)
    CustomRefreshLayout refreshLayout;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.recycle_view2)
    RecyclerView recycleView2;
    private Unbinder unbinder;
    private KnowledgeNavAdapter1 mAdapter1;
    private KnowledgeNavAdapter2 mAdapter2;
    public LinearLayoutManager mLevel1LayoutMgr;
    public LinearLayoutManager mLevel1LayoutMgr2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_knowledge_nav, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        setPresenter(new knowledgeNaviPresenter(this));
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void initView() {
        showLoading();
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getNaviBean();
                refreshLayout.finishRefresh();
            }
        });
        //初始化第一个recyclview
        mAdapter1 = new KnowledgeNavAdapter1();
        mLevel1LayoutMgr=new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(mLevel1LayoutMgr);
        recycleView.setAdapter(mAdapter1);
        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter1.setSelectPos(position);
                recycleView2.scrollToPosition(position);
                LinearLayoutManager mLayoutManager =
                        (LinearLayoutManager) recycleView2.getLayoutManager();
                mLayoutManager.scrollToPositionWithOffset(position, 0);
            }
        });
        //初始化第二个recyclview
        mLevel1LayoutMgr2=new LinearLayoutManager(getContext());
        mAdapter2 = new KnowledgeNavAdapter2();
        recycleView2.setLayoutManager(mLevel1LayoutMgr2);
        recycleView2.setAdapter(mAdapter2);
        recycleView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int pastVisiblesItems = mLevel1LayoutMgr2.findFirstVisibleItemPosition();//得到显示屏内的第一个list的位置数position
                mAdapter1.setSelectPos(pastVisiblesItems);
                recycleView.scrollToPosition(pastVisiblesItems+1);
                LinearLayoutManager mLayoutManager =
                        (LinearLayoutManager) recycleView.getLayoutManager();
                mLayoutManager.scrollToPositionWithOffset(pastVisiblesItems, 0);
//                if (dy > 0) //向下滚动
//                {
//                    int visibleItemCount = mLevel1LayoutMgr2.getChildCount();	//得到显示屏幕内的list数量
//                    int totalItemCount = mLevel1LayoutMgr2.getItemCount();	//得到list的总数量
//                    int pastVisiblesItems = mLevel1LayoutMgr2.findFirstVisibleItemPosition();//得到显示屏内的第一个list的位置数position
//                }
            }

        });

    }



    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
        mPresenter.getNaviBean();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void NaviBeanGet(List<KnowledgeNavBean> data) {
        dismissLoading();
        mAdapter1.setNewData(data);
        mAdapter1.notifyDataSetChanged();
        mAdapter2.setNewData(data);
        mAdapter2.notifyDataSetChanged();
    }
}
