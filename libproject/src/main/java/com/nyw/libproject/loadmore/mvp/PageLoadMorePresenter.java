package com.nyw.libproject.loadmore.mvp;

import com.bakerj.base.loadmore.mvp.LoadMoreContract;
import com.bakerj.base.loadmore.mvp.LoadMorePresenter;
import com.nyw.domain.common.loadmore.PageLoadMoreRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BakerJ
 * @date 2018/6/21
 * 分页方式加载更多
 */
public abstract class PageLoadMorePresenter<View extends LoadMoreContract.View<DestModel>,
        Quest extends PageLoadMoreRequest, SrcModel, DestModel>
        extends LoadMorePresenter<View, Quest, SrcModel, DestModel> {
    protected int Page = 1;
    protected List<DestModel> mData = new ArrayList<>();

    public PageLoadMorePresenter(View view) {
        super(view);
    }

    /**
     * 刷新时当前页码重置
     */
    protected void setUpRefreshBody(Quest body) {
        Page = 1;
        body.setPageSize(10);
        body.setCurrentPage(Page);
    }

    protected void refreshSuccess(List<DestModel> data) {
        mData = data;
        mView.refreshSucceed(new ArrayList<>(data), hasMore(data));
    }

    protected boolean hasMore(List<DestModel> data) {
        return true;
    }

    protected void setUpLoadMoreBody(Quest body) {
        body.setCurrentPage(Page);
    }

    protected void loadMoreSuccess(List<DestModel> data) {
        mData.addAll(data);
        mView.loadMoreSucceed(data, hasMore(data));
    }

    /**
     * 获得数据后页码+1
     */
    protected void doOnSourceGet(List<SrcModel> data) {
        Page++;
    }
}
