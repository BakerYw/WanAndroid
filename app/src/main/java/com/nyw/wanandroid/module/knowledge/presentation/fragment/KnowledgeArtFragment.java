package com.nyw.wanandroid.module.knowledge.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bakerj.base.fragment.BasePresenterFragment;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.wanandroid.R;


@Route(path = PathConstants.PATH_KNOWLEDG_ART)
public class KnowledgeArtFragment extends BasePresenterFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_knowledge_art, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
}
