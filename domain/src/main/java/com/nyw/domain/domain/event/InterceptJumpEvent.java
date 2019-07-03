package com.nyw.domain.domain.event;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author BakerJ
 * @date 2018/5/17
 */
public class InterceptJumpEvent implements IInterceptEvent {
    public static final Parcelable.Creator<InterceptJumpEvent> CREATOR = new Parcelable.Creator<InterceptJumpEvent>() {
        @Override
        public InterceptJumpEvent createFromParcel(Parcel source) {
            return new InterceptJumpEvent(source);
        }

        @Override
        public InterceptJumpEvent[] newArray(int size) {
            return new InterceptJumpEvent[size];
        }
    };
    private String path;
    private Bundle bundle;

    public InterceptJumpEvent() {
    }

    protected InterceptJumpEvent(Parcel in) {
        this.path = in.readString();
        this.bundle = in.readBundle();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeBundle(this.bundle);
    }
}
