package com.nyw.libproject.loadmore.mvp;

import com.bakerj.base.loadmore.mvp.LoadMoreContract;
import com.nyw.domain.common.loadmore.PageLoadMoreRequest;

/**
 * 简易分页逻辑，源数据和目标数据为相同Model
 *
 * @param <View>  View
 * @param <Model> Model
 */
public abstract class SimplePageLoadMorePresenter<View extends LoadMoreContract.View<Model>, Model>
        extends PageLoadMorePresenter<View, PageLoadMoreRequest, Model, Model> {
    public SimplePageLoadMorePresenter(View view) {
        super(view);
    }

    @Override
    protected PageLoadMoreRequest getQuestBody() {
        return new PageLoadMoreRequest();
    }

    @Override
    protected Model castDataToDest(Model model) {
        return model;
    }
}
