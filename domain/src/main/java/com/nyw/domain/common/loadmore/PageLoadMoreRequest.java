package com.nyw.domain.common.loadmore;

/**
 * @author BakerJ
 * @date 2018/6/21
 */
public class PageLoadMoreRequest {
    private int currentPage;
    private int pageSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
