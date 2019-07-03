package com.nyw.domain.common.loadmore;


/**
 * @author BakerJ
 * @date 2017/12/25
 */

public class LastItemLoadMoreRequest {
    private long lastItemId = 0;
    private int itemCount;

    public long getLastItemId() {
        return lastItemId;
    }

    public void setLastItemId(long lastItemId) {
        this.lastItemId = lastItemId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
