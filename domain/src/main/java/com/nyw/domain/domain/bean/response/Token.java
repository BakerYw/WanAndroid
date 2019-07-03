package com.nyw.domain.domain.bean.response;

/**
 * @author BakerJ
 * @date 2018/3/22
 */

public class Token {
    private String accessToken;
    private long createTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
