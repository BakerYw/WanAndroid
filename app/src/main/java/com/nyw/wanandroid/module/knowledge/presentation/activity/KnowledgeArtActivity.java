package com.nyw.wanandroid.module.knowledge.presentation.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.google.android.material.tabs.TabLayout;
import com.nyw.domain.domain.bean.response.home.KnowledgeArtBean;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.activity.WanBaseActivity;
import com.nyw.libproject.common.adapter.BasePagerAdapter;
import com.nyw.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;


@Route(path = PathConstants.PATH_KNOWLEDGART_ACTIVITY)
public class KnowledgeArtActivity extends WanBaseActivity {

    @BindView(R.id.art_tab)
    TabLayout artTab;
    @BindView(R.id.art_vp)
    ViewPager artVp;
    @State
    @Autowired
    KnowledgeArtBean knowledgeArtBean;
    @State
    @Autowired
    int pos;
    @BindView(R.id.common_tv_title)
    TextView commonTvTitle;
    @BindView(R.id.common_iv_back)
    ImageView commonIvBack;
    BasePagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_art);
        ButterKnife.bind(this);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        initView();
    }

    private void initView() {
        commonTvTitle.setText(knowledgeArtBean.getName());
        commonIvBack.setOnClickListener(v -> finish());
        int size = knowledgeArtBean.getChildren().size();
        Fragment[] fragments = new Fragment[size];
        String[] title = new String[size];
        for (int i = 0; i < knowledgeArtBean.getChildren().size(); i++) {
            fragments[i] = Navigation.getArtDetailFragment(knowledgeArtBean.getChildren().get(i).getId());
            title[i] =knowledgeArtBean.getChildren().get(i).getName();
        }
        mAdapter = new BasePagerAdapter(getSupportFragmentManager(), fragments) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        };
        artVp.setAdapter(mAdapter);
        artTab.setupWithViewPager(artVp);
        artVp.setCurrentItem(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BarUtils.setStatusBarLightMode(this, false);
    }
}
