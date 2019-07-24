package com.nyw.wanandroid.module.home.mvp;

import com.bakerj.rxretrohttp.RxRetroHttp;
import com.nyw.domain.domain.bean.response.home.HotkeyBean;
import com.nyw.libproject.common.api.CBApiObserver;
import com.nyw.wanandroid.module.home.data.repository.homeRepositoryImpl;
import com.nyw.wanandroid.utils.SearchHistoryUtils;

import java.util.List;

/**
 * @author nyw
 * @date 2019/07/10
 *
 * Generated by MVPGenerator
 */
public class SearchHistoryPresenter extends SearchHistoryContract.Presenter{
    private homeRepositoryImpl mRepository = new homeRepositoryImpl();
    private final SearchHistoryUtils mSearchHistoryUtils = SearchHistoryUtils.newInstance();


    public SearchHistoryPresenter(SearchHistoryContract.View view) {
        super(view);
    }

    @Override
    public void getHotkeyBean() {
        RxRetroHttp.composeRequest(mRepository.getHotkeyBean(), mView)
                .subscribe(new CBApiObserver<List<HotkeyBean>>() {
                    @Override
                    protected void success(List<HotkeyBean> data) {
                        ((SearchHistoryContract.View) mView).HotkeyBeanGet(data);
                    }
                });
    }

    public List<String> getHistory(){
        return mSearchHistoryUtils.get();
    }

    public void saveHistory(List<String> list){
        List<String> saves = list;
        int max = 10;
        if (list != null && list.size() > max) {
            saves = list.subList(0, max - 1);
        }
        mSearchHistoryUtils.save(saves);
    }

}
