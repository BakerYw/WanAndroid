package com.nyw.wanandroid.module.me.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBasePresenterFragment;
import com.nyw.wanandroid.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


@Route(path = PathConstants.PATH_ME)
public class MyFragment extends WanBasePresenterFragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        //setPresenter(new projectPresenter(this));
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
