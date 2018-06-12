package com.fx.bean;

import com.fx.model.AutoDetectionLabel;

/**
 * Created by thinkpad on 2018/6/12.
 */
public class AutoDetectionLabelBean {
    private int missionid;
    private AutoDetectionLabel autoDetectionLabel;

    public int getMissionid() {
        return missionid;
    }

    public void setMissionid(int missionid) {
        this.missionid = missionid;
    }

    public AutoDetectionLabel getAutoDetectionLabel() {
        return autoDetectionLabel;
    }

    public void setAutoDetectionLabel(AutoDetectionLabel autoDetectionLabel) {
        this.autoDetectionLabel = autoDetectionLabel;
    }
}
