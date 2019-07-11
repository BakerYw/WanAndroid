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
import com.nyw.domain.domain.bean.response.home.KnowledgeArtBean;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBasePresenterFragment;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.knowledge.mvp.knowledgeArtContract;
import com.nyw.wanandroid.module.knowledge.mvp.knowledgeArtPresenter;
import com.nyw.wanandroid.module.knowledge.presentation.adapter.KnowledgeArtAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@Route(path = PathConstants.PATH_KNOWLEDG_ART)
public class KnowledgeArtFragment extends WanBasePresenterFragment<knowledgeArtPresenter> implements knowledgeArtContract.View {


    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.refresh_layout)
    CustomRefreshLayout refreshLayout;
    private Unbinder unbinder;
    private KnowledgeArtAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_knowledge_art, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        setPresenter(new knowledgeArtPresenter(this));
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void initView() {
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getThreeBean();
                refreshLayout.finishRefresh();
            }
        });
        mAdapter=new KnowledgeArtAdapter();
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(mAdapter);
    }

    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
        showLoading();
        mPresenter.getThreeBean();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ThreeBeanGet(List<KnowledgeArtBean> data) {
        dismissLoading();
        if (data!=null){
            mAdapter.addData(0, data);
            mAdapter.notifyDataSetChanged();
        }
    }
}
