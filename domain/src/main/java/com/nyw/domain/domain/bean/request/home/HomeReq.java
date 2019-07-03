package com.nyw.domain.domain.bean.request.home;

import com.nyw.domain.common.loadmore.PageLoadMoreRequest;

public class HomeReq extends PageLoadMoreRequest {
    private int page;

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
}
