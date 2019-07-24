package com.nyw.wanandroid.module.home.presentation.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.activity.WanBaseActivity;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.presentation.fragment.SearchHistoryFragment;
import com.nyw.wanandroid.module.home.presentation.fragment.SearchResultFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import razerdp.util.InputMethodUtils;

@Route(path = PathConstants.PATH_SEARCH)
public class SearchActivity extends WanBaseActivity {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tv_search_cancel)
    TextView tvSearchCancel;
    private SearchHistoryFragment mSearchHistoryFragment;
    private SearchResultFragment mSearchResultFragment;
    private FragmentManager  mFm = getSupportFragmentManager();
    private boolean mIsResultPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        initView();
    }

    private void initView() {
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String keyword = etSearch.getText().toString();
                if (!TextUtils.isEmpty(keyword)) {
                    search(keyword);
                }
                return true;
            }
            return false;
        });
        Fragment searchHistoryFragment = mFm.findFragmentByTag(SearchHistoryFragment.class.getName());
        if (searchHistoryFragment == null) {
            mSearchHistoryFragment = new SearchHistoryFragment();
        } else {
            mSearchHistoryFragment = (SearchHistoryFragment) searchHistoryFragment;
        }
        Fragment searchResultFragment = mFm.findFragmentByTag(SearchResultFragment.class.getName());
        if (searchResultFragment == null) {
            mSearchResultFragment = new SearchResultFragment();
        } else {
            mSearchResultFragment = (SearchResultFragment) searchResultFragment;
        }
        FragmentTransaction t = mFm.beginTransaction();
        t.add(R.id.fl, mSearchHistoryFragment, SearchHistoryFragment.class.getName());
        t.add(R.id.fl, mSearchResultFragment, SearchResultFragment.class.getName());
        t.show(mSearchHistoryFragment);
        t.hide(mSearchResultFragment);
        t.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BarUtils.setStatusBarLightMode(this, false);
    }

    @OnClick(R.id.tv_search_cancel)
    public void onViewClicked() {
        if (mIsResultPage) {
            showHistoryFragment();
        } else {
            finish();
        }
    }

    public void search(String key) {
        InputMethodUtils.close(etSearch);
        etSearch.clearFocus();
        if (TextUtils.isEmpty(key)) {
            if (mIsResultPage) {
                showHistoryFragment();
            }
        } else {
            setTextWithSelection(etSearch, key);
            if (!mIsResultPage) {
                showResultFragment();
            }
            mSearchHistoryFragment.addHistory(key);
            mSearchResultFragment.search(key);
        }
    }
    public void setTextWithSelection(EditText editText, CharSequence text) {
        editText.setText(text);
        editText.setSelection(editText.getText().toString().length());
    }
    private void showHistoryFragment() {
        mIsResultPage = false;
        FragmentTransaction t = mFm.beginTransaction();
        t.hide(mSearchResultFragment);
        t.show(mSearchHistoryFragment);
        t.commit();
    }

    private void showResultFragment() {
        mIsResultPage = true;
        FragmentTransaction t = mFm.beginTransaction();
        t.hide(mSearchHistoryFragment);
        t.show(mSearchResultFragment);
        t.commit();
    }
}
