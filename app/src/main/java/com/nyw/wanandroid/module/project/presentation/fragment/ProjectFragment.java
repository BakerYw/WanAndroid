package com.nyw.wanandroid.module.project.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.nyw.domain.domain.bean.response.home.KnowledgeArtBean;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.adapter.BasePagerAdapter;
import com.nyw.libproject.common.fragment.WanBasePresenterFragment;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.project.mvp.projectContract;
import com.nyw.wanandroid.module.project.mvp.projectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = PathConstants.PATH_PROJECT)
public class ProjectFragment extends WanBasePresenterFragment <projectPresenter> implements projectContract.View{
    @BindView(R.id.mall_tab)
    TabLayout mallTab;
    @BindView(R.id.mall_vp)
    ViewPager mallVp;
    private Unbinder unbinder;
    BasePagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_wechat, container, false);
        unbinder = ButterKnife.bind(this, view);
        setPresenter(new projectPresenter(this));
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
        mPresenter.getProjectTabList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ProjectTabGet(List<KnowledgeArtBean> data) {
        int size = data.size();
        Fragment[] fragments = new Fragment[size];
        String[] title = new String[size];
        for (int i = 0; i < size; i++) {
            KnowledgeArtBean knowledgeArtBean = data.get(i);
            fragments[i] = Navigation.getProjectListFragment(data.get(i).getId());
            title[i] = knowledgeArtBean.getName();
        }
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), fragments) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        };
        mallVp.setAdapter(mAdapter);
        mallVp.setOffscreenPageLimit(size);
        mallTab.setupWithViewPager(mallVp);
    }
}
