package com.nyw.wanandroid.module.home.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.domain.bean.response.home.HotkeyBean;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBasePresenterFragment;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.mvp.SearchHistoryContract;
import com.nyw.wanandroid.module.home.mvp.SearchHistoryPresenter;
import com.nyw.wanandroid.module.home.presentation.activity.SearchActivity;
import com.nyw.wanandroid.module.home.presentation.adapter.HotSearchAdapter;
import com.nyw.wanandroid.module.knowledge.mvp.KnowledgeArtPresenter;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchHistoryFragment extends WanBasePresenterFragment<SearchHistoryPresenter> implements SearchHistoryContract.View {

    private Unbinder unbinder;
    @BindView(R.id.searchHot_rv)
    RecyclerView rv_hot;
    @BindView(R.id.rv_history)
    RecyclerView rv_history;
    private HotSearchAdapter mHotAdapter=new HotSearchAdapter();
    private BaseQuickAdapter<String, BaseViewHolder> mHistoryAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        setPresenter(new SearchHistoryPresenter(this));
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void initView() {
        rv_hot.setNestedScrollingEnabled(false);
        rv_hot.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv_hot.setAdapter(mHotAdapter);

        mHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                search(mHotAdapter.getData().get(position).getName());
            }
        });


        rv_history.setLayoutManager(new LinearLayoutManager(getContext()));
        mHistoryAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item_search_history) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_key, item)
                        .addOnClickListener(R.id.iv_remove);
            }
        };
        mHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String key = mHistoryAdapter.getItem(position);
                search(key);
            }
        });
        mHistoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mHistoryAdapter.remove(position);
                mPresenter.saveHistory(mHistoryAdapter.getData());
            }
        });
        rv_history.setAdapter(mHistoryAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getHotkeyBean();
        mHistoryAdapter.setNewData(mPresenter.getHistory());
    }


    @Override
    public void HotkeyBeanGet(List<HotkeyBean> data) {
        mHotAdapter.setNewData(data);
        mHotAdapter.notifyDataSetChanged();
    }
    public void addHistory(String key) {
        List<String> datas = mHistoryAdapter.getData();
        int index = datas.indexOf(key);
        if (index == 0) {
            return;
        }
        if (index >= 0) {
            Collections.swap(datas, index, 0);
            mHistoryAdapter.notifyItemMoved(index, 0);
        } else {
            mHistoryAdapter.addData(0, key);
            int max = 10;
            List<String> list = mHistoryAdapter.getData();
            if (list.size() > max) {
                mHistoryAdapter.remove(list.size() - 1);
            }
        }
        mPresenter.saveHistory(mHistoryAdapter.getData());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void search(String key) {
        if (getActivity() instanceof SearchActivity) {
            SearchActivity activity = (SearchActivity) getActivity();
            activity.search(key);
        }
    }

}
