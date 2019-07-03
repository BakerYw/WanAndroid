package com.nyw.libthird.sharelib;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author BakerJ
 * @date 2017/12/29
 */

public class ThirdPartyLoginInfo implements Parcelable {
    public static final Creator<ThirdPartyLoginInfo> CREATOR = new Creator<ThirdPartyLoginInfo>() {
        @Override
        public ThirdPartyLoginInfo createFromParcel(Parcel source) {
            return new ThirdPartyLoginInfo(source);
        }

        @Override
        public ThirdPartyLoginInfo[] newArray(int size) {
            return new ThirdPartyLoginInfo[size];
        }
    };
    private String openId;
    private String name;
    private String gender;
    private String iconUrl;
    private String accessToken;
    private String userId;


    public ThirdPartyLoginInfo() {
    }

    protected ThirdPartyLoginInfo(Parcel in) {
        this.openId = in.readString();
        this.name = in.readString();
        this.gender = in.readString();
        this.iconUrl = in.readString();
        this.accessToken = in.readString();
        this.userId = in.readString();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.openId);
        dest.writeString(this.name);
        dest.writeString(this.gender);
        dest.writeString(this.iconUrl);
        dest.writeString(this.accessToken);
        dest.writeString(this.userId);
    }
}
