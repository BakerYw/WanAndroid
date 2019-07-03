package com.nyw.libproject.common.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private Fragment[] mFragmentList;

    public BasePagerAdapter(FragmentManager fm, Fragment... fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList[position];
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.length;
    }

    public void setFragmentList(Fragment[] fragmentList) {
        this.mFragmentList = fragmentList;
    }
}
