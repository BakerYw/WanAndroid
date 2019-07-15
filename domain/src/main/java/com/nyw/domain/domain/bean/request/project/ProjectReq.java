package com.nyw.domain.domain.bean.request.project;

import com.nyw.domain.common.loadmore.PageLoadMoreRequest;

public class ProjectReq extends PageLoadMoreRequest {
    private int page;
    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
}
