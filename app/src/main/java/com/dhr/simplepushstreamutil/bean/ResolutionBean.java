package com.dhr.simplepushstreamutil.bean;

import java.io.Serializable;

public class ResolutionBean implements Serializable {
    private String resolutionNo;
    private String resolutionPx;
    private String fps;

    public String getResolutionNo() {
        return resolutionNo;
    }

    public void setResolutionNo(String resolutionNo) {
        this.resolutionNo = resolutionNo;
    }

    public String getResolutionPx() {
        return resolutionPx;
    }

    public void setResolutionPx(String resolutionPx) {
        this.resolutionPx = resolutionPx;
    }

    public String getFps() {
        return fps;
    }

    public void setFps(String fps) {
        this.fps = fps;
    }
}
