package com.nyw.wanandroid.module.knowledge.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.adapter.BasePagerAdapter;
import com.nyw.libproject.common.fragment.WanBasePresenterFragment;
import com.nyw.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@Route(path = PathConstants.PATH_KNOWLEDGE)
public class KnowledgeFragment extends WanBasePresenterFragment {

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
        View view = inflater.inflate(R.layout.frag_knowledge, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void initView() {
        Fragment[] fragments = new Fragment[2];
        String[]title = new String[]{"体系","导航"};
        for (int i = 0; i < 2; i++) {
            fragments[0] = Navigation.getArtFragment();
            fragments[1] = Navigation.getNavFragment();
        }
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), fragments) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        };
        mallVp.setAdapter(mAdapter);
        mallVp.setOffscreenPageLimit(2);
        mallTab.setupWithViewPager(mallVp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
