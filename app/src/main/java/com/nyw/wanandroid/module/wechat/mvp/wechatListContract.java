package com.nyw.wanandroid.module.wechat.mvp;

import com.bakerj.base.loadmore.mvp.LoadMoreContract;
import com.nyw.domain.domain.bean.request.wechat.WechatReq;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.libproject.loadmore.mvp.PageLoadMorePresenter;

/**
 * @author nyw
 * @date 2019/07/10
 *
 * Generated by MVPGenerator
 */
public interface wechatListContract {
    //泛型为返回的数据类型，即Adapter中展示的数据类型
    interface View extends LoadMoreContract.View<ArticleBean> {

    }

    //具体泛型注释可查阅 {父基类LoadMorePresenter}
    abstract class Presenter extends PageLoadMorePresenter<wechatListContract.View, WechatReq, ArticleBean,
            ArticleBean> {
        public Presenter(wechatListContract.View view) {
            super(view);
        }
    }
}