package com.nyw.wanandroid.module.home.mvp;


import com.nyw.domain.common.loadmore.PageLoadMoreResponse;
import com.nyw.domain.domain.bean.request.home.SearchReq;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.wanandroid.module.home.data.repository.IhomeRepository;
import com.nyw.wanandroid.module.home.data.repository.homeRepositoryImpl;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author nyw
 * @date 2019/07/10
 *
 * Generated by MVPGenerator
 */
public class SearchResultPresenter extends SearchResultContract.Presenter{
    private IhomeRepository mRepository = new homeRepositoryImpl();

    private String mkey;

    public SearchResultPresenter(SearchResultContract.View view) {
        super(view);
    }

    @Override
    public void setBody(String key) {
        this.mkey=key;
    }

    @Override
    protected void setUpRefreshBody(SearchReq body) {
        super.setUpRefreshBody(body);
        body.setK(mkey);
        body.setPage(0);
    }

    @Override
    protected SearchReq getQuestBody() {
        SearchReq req=new SearchReq();
        req.setPage(0);
        req.setK(mkey);
        return req;
    }

    @Override
    protected ArticleBean castDataToDest(ArticleBean articleBean) {
        return articleBean;
    }

    @Override
    protected Observable<List<ArticleBean>> getRefreshLoadObservable(SearchReq body) {
        return mRepository.SearchBean(body).map(PageLoadMoreResponse::getDatas);
    }

}
