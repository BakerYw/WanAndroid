package com.nyw.libproject.loadmore.mvp;

import com.bakerj.base.loadmore.mvp.LoadMoreContract;
import com.bakerj.base.loadmore.mvp.LoadMorePresenter;
import com.nyw.domain.common.loadmore.DataContainsId;
import com.nyw.domain.common.loadmore.LastItemLoadMoreRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <SrcModel> 元数据需包含Id
 * @author BakerJ
 * @date 2018/4/19
 * LastItem方式加载更多
 */
public abstract class LastItemLoadMorePresenter<View extends LoadMoreContract.View<DestModel>,
        Quest extends LastItemLoadMoreRequest, SrcModel extends DataContainsId, DestModel>
        extends LoadMorePresenter<View, Quest, SrcModel, DestModel> {
    protected long mLastItemId;
    protected int mCurrentSize;
    protected List<DestModel> mData = new ArrayList<>();

    public LastItemLoadMorePresenter(View view) {
        super(view);
    }

    /**
     * 首次刷新lastItemId为0
     */
    protected void setUpRefreshBody(Quest body) {
        body.setLastItemId(0);
    }

    protected void refreshSuccess(List<DestModel> data) {
        mData = data;
        mView.refreshSucceed(new ArrayList<>(data), hasMore(data));
    }

    protected boolean hasMore(List<DestModel> data) {
        return true;
    }

    protected void setUpLoadMoreBody(Quest body) {
        body.setLastItemId(mLastItemId);
    }

    protected void loadMoreSuccess(List<DestModel> data) {
        mData.addAll(data);
        mView.loadMoreSucceed(data, hasMore(data));
    }

    /**
     * 得到数据后，更新lastItemId为最后一个数据的id
     */
    protected void doOnSourceGet(List<SrcModel> data) {
        mCurrentSize = data.size();
        mLastItemId = data.get(mCurrentSize - 1).getId();
    }
}
