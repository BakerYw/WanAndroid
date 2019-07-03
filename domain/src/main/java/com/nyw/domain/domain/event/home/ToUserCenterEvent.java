package com.nyw.domain.domain.event.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.nyw.domain.domain.event.BaseEvent;
import com.nyw.domain.domain.event.IInterceptEvent;


/**
 * @author BakerJ
 * @date 2018/4/19
 */
public class ToUserCenterEvent extends BaseEvent implements IInterceptEvent {
    public static final Parcelable.Creator<ToUserCenterEvent> CREATOR =
            new Parcelable.Creator<ToUserCenterEvent>() {
        @Override
        public ToUserCenterEvent createFromParcel(Parcel source) {
            return new ToUserCenterEvent(source);
        }

        @Override
        public ToUserCenterEvent[] newArray(int size) {
            return new ToUserCenterEvent[size];
        }
    };

    public ToUserCenterEvent() {
    }

    protected ToUserCenterEvent(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
