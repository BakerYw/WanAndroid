package com.nyw.wanandroid.module.wechat.mvp;

import com.bakerj.rxretrohttp.RxRetroHttp;
import com.nyw.domain.common.api.WanApiResult;
import com.nyw.domain.common.loadmore.PageLoadMoreResponse;
import com.nyw.domain.domain.bean.request.wechat.WechatReq;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.domain.domain.event.home.CollectionEvent;
import com.nyw.libproject.common.api.CBApiObserver;
import com.nyw.wanandroid.module.home.data.repository.IhomeRepository;
import com.nyw.wanandroid.module.home.data.repository.homeRepositoryImpl;
import com.nyw.wanandroid.module.home.mvp.HomeContract;
import com.nyw.wanandroid.module.wechat.data.repository.IwechatRepository;
import com.nyw.wanandroid.module.wechat.data.repository.wechatRepositoryImpl;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author nyw
 * @date 2019/07/10
 *
 * Generated by MVPGenerator
 */
public class wechatListPresenter extends wechatListContract.Presenter{
    private IwechatRepository mRepository = new wechatRepositoryImpl();
    private IhomeRepository homeRepository = new homeRepositoryImpl();

    private int id;

    public wechatListPresenter(wechatListContract.View view,int id) {
        super(view);
        this.id=id;
    }

    @Override
    protected WechatReq getQuestBody() {
        WechatReq req = new WechatReq();
        req.setId(id);
        return req;
    }
    @Override
    public void Collect(int id) {
        RxRetroHttp.composeRequest(homeRepository.Collect(id), mView)
                .subscribe(new CBApiObserver<WanApiResult>() {
                    @Override
                    protected void success(WanApiResult data) {
                        ((wechatListContract.View) mView).CollectSuccess();
                        CollectionEvent.postCollectWithArticleId(id);
                    }
                });
    }

    @Override
    public void UnCollect(int id) {
        RxRetroHttp.composeRequest(homeRepository.Uncollect(id), mView)
                .subscribe(new CBApiObserver<WanApiResult>() {
                    @Override
                    protected void success(WanApiResult data) {
                        ((wechatListContract.View) mView).UnCollectSuccess();
                        CollectionEvent.postUnCollectWithArticleId(id);
                    }
                });
    }
    @Override
    protected void setUpRefreshBody(WechatReq body) {
        super.setUpRefreshBody(body);
        body.setId(id);
    }

    @Override
    protected boolean hasMore(List<ArticleBean> data) {
        return true;
    }

    @Override
    protected ArticleBean castDataToDest(ArticleBean articleBean) {
        return articleBean;
    }

    @Override
    protected Observable<List<ArticleBean>> getRefreshLoadObservable(WechatReq body) {
        return mRepository.getArticleBean(body).map(PageLoadMoreResponse::getDatas);
    }
}
