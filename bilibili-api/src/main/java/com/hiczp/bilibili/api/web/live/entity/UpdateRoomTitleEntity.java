package com.hiczp.bilibili.api.web.live.entity;

public class UpdateRoomTitleEntity {

    /**
     * code : 65530
     * msg : invalid request
     * message : invalid request
     * data : bad token
     */

    private int code;
    private String msg;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
