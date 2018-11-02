package com.hiczp.bilibili.api.web.live.entity;

import java.util.List;

public class LiveRoomStreamInfoEntity {

    /**
     * code : 0
     * msg : ok
     * message : ok
     * data : {"rtmp":{"addr":"rtmp://js.live-send.acg.tv/live-js/","code":"?streamname=live_2335148_7737098&key=cd7573e18568799dab29522345545aec"},"stream_line":[{"name":"默认线路","src":68,"cdn_name":"js","checked":1}]}
     */

    private int code;
    private String msg;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rtmp : {"addr":"rtmp://js.live-send.acg.tv/live-js/","code":"?streamname=live_2335148_7737098&key=cd7573e18568799dab29522345545aec"}
         * stream_line : [{"name":"默认线路","src":68,"cdn_name":"js","checked":1}]
         */

        private RtmpBean rtmp;
        private List<StreamLineBean> stream_line;

        public RtmpBean getRtmp() {
            return rtmp;
        }

        public void setRtmp(RtmpBean rtmp) {
            this.rtmp = rtmp;
        }

        public List<StreamLineBean> getStream_line() {
            return stream_line;
        }

        public void setStream_line(List<StreamLineBean> stream_line) {
            this.stream_line = stream_line;
        }

        public static class RtmpBean {
            /**
             * addr : rtmp://js.live-send.acg.tv/live-js/
             * code : ?streamname=live_2335148_7737098&key=cd7573e18568799dab29522345545aec
             */

            private String addr;
            private String code;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }

        public static class StreamLineBean {
            /**
             * name : 默认线路
             * src : 68
             * cdn_name : js
             * checked : 1
             */

            private String name;
            private int src;
            private String cdn_name;
            private int checked;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSrc() {
                return src;
            }

            public void setSrc(int src) {
                this.src = src;
            }

            public String getCdn_name() {
                return cdn_name;
            }

            public void setCdn_name(String cdn_name) {
                this.cdn_name = cdn_name;
            }

            public int getChecked() {
                return checked;
            }

            public void setChecked(int checked) {
                this.checked = checked;
            }
        }
    }
}
