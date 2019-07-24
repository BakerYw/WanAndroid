package com.nyw.domain.domain.bean.request.home;

import com.nyw.domain.common.loadmore.PageLoadMoreRequest;

public class SearchReq extends PageLoadMoreRequest {
    private String k;
    private int page;

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }
}
