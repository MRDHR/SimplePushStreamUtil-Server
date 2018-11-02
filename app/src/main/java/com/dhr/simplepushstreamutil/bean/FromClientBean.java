package com.dhr.simplepushstreamutil.bean;


public class FromClientBean {
    private int type;
    private int schemeType;
    private String cmd;
    private bilibiliLiveInfo bilibiliLiveInfo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(int schemeType) {
        this.schemeType = schemeType;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public FromClientBean.bilibiliLiveInfo getBilibiliLiveInfo() {
        return bilibiliLiveInfo;
    }

    public void setBilibiliLiveInfo(FromClientBean.bilibiliLiveInfo bilibiliLiveInfo) {
        this.bilibiliLiveInfo = bilibiliLiveInfo;
    }

    public class bilibiliLiveInfo {
        private String userName;
        private String password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
