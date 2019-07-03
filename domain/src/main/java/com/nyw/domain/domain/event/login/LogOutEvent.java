package com.nyw.domain.domain.event.login;


import com.nyw.domain.domain.event.BaseEvent;

/**
 * @author BakerJ
 * @date 2018/3/29
 */
public class LogOutEvent extends BaseEvent {
    private boolean isManual;

    public LogOutEvent(boolean isManual) {
        this.isManual = isManual;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }
}
