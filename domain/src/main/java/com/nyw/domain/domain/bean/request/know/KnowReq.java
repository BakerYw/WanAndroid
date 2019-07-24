package com.nyw.domain.domain.bean.request.know;

import com.nyw.domain.common.loadmore.PageLoadMoreRequest;

public class KnowReq extends PageLoadMoreRequest {
    private int cid;
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
