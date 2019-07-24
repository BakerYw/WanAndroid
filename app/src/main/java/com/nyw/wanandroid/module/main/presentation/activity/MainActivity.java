package com.nyw.wanandroid.module.main.presentation.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.activity.WanBaseActivity;
import com.nyw.libproject.common.adapter.BasePagerAdapter;
import com.nyw.libwidgets.scroll.TouchHandleViewPager;
import com.nyw.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = PathConstants.PATH_HOME)
public class MainActivity extends WanBaseActivity {
    @BindView(R.id.home_nav)
    BottomNavigationViewEx homeNav;
    @BindView(R.id.home_vp)
    TouchHandleViewPager homeVp;
    private int preIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        initVP();
        initNav();
    }

    private void initNav() {
        homeVp.setCanTouch(false);
        homeVp.setOffscreenPageLimit(5);
        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager(),
                Navigation.getCircleFragment(), Navigation.getKnowledgeFragment(),
                Navigation.getWechatFragment(), Navigation.getProjectFragment(), Navigation.getMeFragment());
        homeVp.setAdapter(adapter);
    }

    private void initVP() {
        homeNav.enableAnimation(false);
        homeNav.enableShiftingMode(false);
        homeNav.enableItemShiftingMode(false);
        homeNav.setTextSize(10);
        homeNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    preIndex = 0;
                    homeVp.setCurrentItem(0, false);
                    break;
                case R.id.nav_knowledge:
                    preIndex = 1;
                    homeVp.setCurrentItem(1, false);
                    break;
                case R.id.nav_wechat:
                    preIndex = 2;
                    homeVp.setCurrentItem(2, false);
                    break;
                case R.id.nav_project:
                    preIndex = 3;
                    homeVp.setCurrentItem(3, false);
                    break;
                case R.id.nav_me:
                    preIndex = 4;
                    homeVp.setCurrentItem(4, false);
                    break;
                default:
                    homeNav.postDelayed(() -> homeNav.setCurrentItem(preIndex), 500);
                    break;
            }
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BarUtils.setStatusBarLightMode(this, false);
    }


}
