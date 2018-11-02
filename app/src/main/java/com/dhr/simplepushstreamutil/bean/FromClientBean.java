package com.dhr.simplepushstreamutil.bean;


import java.util.PrimitiveIterator;

public class FromClientBean {
    private int type;
    private int schemeType;
    private String cmd;
    private bilibiliAccountInfo bilibiliAccountInfo;
    private bilibiliRoomInfo bilibiliRoomInfo;

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

    public bilibiliAccountInfo getBilibiliAccountInfo() {
        return bilibiliAccountInfo;
    }

    public void setBilibiliAccountInfo(bilibiliAccountInfo bilibiliAccountInfo) {
        this.bilibiliAccountInfo = bilibiliAccountInfo;
    }

    public FromClientBean.bilibiliRoomInfo getBilibiliRoomInfo() {
        return bilibiliRoomInfo;
    }

    public void setBilibiliRoomInfo(FromClientBean.bilibiliRoomInfo bilibiliRoomInfo) {
        this.bilibiliRoomInfo = bilibiliRoomInfo;
    }

    public class bilibiliAccountInfo {
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

    public class bilibiliRoomInfo {
        private String roomName;
        private String areaId;

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }
    }

}
