package com.nyw.wanandroid.module.main.presentation.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.FragmentUtils;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nyw.domain.common.util.cache.UserCacheUtil;
import com.nyw.domain.domain.event.home.ToUserCenterEvent;
import com.nyw.domain.domain.event.login.LogOutEvent;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.activity.WanBaseActivity;
import com.nyw.libproject.common.adapter.BasePagerAdapter;
import com.nyw.libwidgets.scroll.TouchHandleViewPager;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.me.presentation.fragment.MyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = PathConstants.PATH_HOME)
public class MainActivity extends WanBaseActivity {
    @BindView(R.id.home_nav)
    BottomNavigationViewEx homeNav;
    @BindView(R.id.home_vp)
    TouchHandleViewPager homeVp;
    private static final int INDEX_HOME = 0, INDEX_KNOW = 1,INDEX_WECHAT =2,
            INDEX_PROJECT = 3, INDEX_ME = 4;
    private int preIndex = INDEX_HOME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
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
                    preIndex = INDEX_HOME;
                    homeVp.setCurrentItem(INDEX_HOME, false);
                    break;
                case R.id.nav_knowledge:
                    preIndex = INDEX_KNOW;
                    homeVp.setCurrentItem(INDEX_KNOW, false);
                    break;
                case R.id.nav_wechat:
                    preIndex = INDEX_WECHAT;
                    homeVp.setCurrentItem(INDEX_WECHAT, false);
                    break;
                case R.id.nav_project:
                    preIndex = INDEX_PROJECT;
                    homeVp.setCurrentItem(INDEX_PROJECT, false);
                    break;
                default:
                    if (UserCacheUtil.checkIsLoginWithJump(new ToUserCenterEvent())) {
                        preIndex = INDEX_ME;
                        homeVp.setCurrentItem(INDEX_ME, false);
                    } else {
                        homeNav.postDelayed(() -> homeNav.setCurrentItem(preIndex), 500);
                    }
                    break;
            }
            return true;
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void toMe(ToUserCenterEvent event) {
        homeNav.setCurrentItem(INDEX_ME);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logout(LogOutEvent event) {
        ActivityUtils.finishOtherActivities(MainActivity.class);
        homeNav.setCurrentItem(INDEX_HOME);
    }
    @Override
    protected void onResume() {
        super.onResume();
        BarUtils.setStatusBarLightMode(this, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment mFragment : fragments) {
                mFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
