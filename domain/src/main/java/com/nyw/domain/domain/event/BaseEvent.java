package com.nyw.domain.domain.event;

import org.greenrobot.eventbus.EventBus;

/**
 * @author BakerJ
 * @date 2018/4/19
 */
public abstract class BaseEvent {
    public void post(){
        EventBus.getDefault().post(this);
    }
}
