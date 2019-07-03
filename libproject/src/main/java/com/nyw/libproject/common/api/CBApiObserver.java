package com.nyw.libproject.common.api;

import com.bakerj.rxretrohttp.interfaces.IBaseApiAction;
import com.bakerj.rxretrohttp.subscriber.ApiObserver;

public abstract class CBApiObserver<T> extends ApiObserver<T> {
    public CBApiObserver() {
    }

    public CBApiObserver(IBaseApiAction apiAction) {
        super(apiAction);
    }

    public CBApiObserver(IBaseApiAction apiAction, boolean isShowLoading, boolean isShowMsg) {
        super(apiAction, isShowLoading, isShowMsg);
    }
}
