package com.nyw.domain.domain.bean.request.wechat;

import com.nyw.domain.common.loadmore.PageLoadMoreRequest;

public class WechatReq extends PageLoadMoreRequest {
    private int id;
    private int page;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
}
