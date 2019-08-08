package com.nyw.domain.domain.event.setting;

import com.nyw.domain.domain.event.BaseEvent;


public class SettingChangeEvent extends BaseEvent {

    private boolean showTopChanged;
    private boolean rvAnimChanged;
    private boolean showImage;

    public SettingChangeEvent() {
    }


    @Override
    public void post() {
        if (showTopChanged || rvAnimChanged||showImage) {
            super.post();
        }
    }

    public boolean isShowImage() {
        return showImage;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
    }

    public boolean isShowTopChanged() {
        return showTopChanged;
    }
    public void setShowTopChanged(boolean showTopChanged) {
        this.showTopChanged = showTopChanged;
    }


    public boolean isRvAnimChanged() {
        return rvAnimChanged;
    }

    public void setRvAnimChanged(boolean rvAnimChanged) {
        this.rvAnimChanged = rvAnimChanged;
    }
}
