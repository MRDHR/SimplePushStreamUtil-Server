package com.hiczp.bilibili.api.web.live.entity;

public class StartLiveEntity {

    /**
     * code : 0
     * msg :
     * message :
     * data : {"change":1,"status":"LIVE","rtmp":{"addr":"rtmp://txy.live-send.acg.tv/live-txy/","code":"?streamname=live_2335148_7737098&key=cd7573e18568799dab29522345545aec","new_link":"http://tcdns.myqcloud.com:8086/bilibili_redirect?up_rtmp=txy.live-send.acg.tv%2Flive-txy%2F%3Fstreamname%3Dlive_2335148_7737098%26key%3Dcd7573e18568799dab29522345545aec","provider":"txy"},"try_time":"0000-00-00 00:00:00"}
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
         * change : 1
         * status : LIVE
         * rtmp : {"addr":"rtmp://txy.live-send.acg.tv/live-txy/","code":"?streamname=live_2335148_7737098&key=cd7573e18568799dab29522345545aec","new_link":"http://tcdns.myqcloud.com:8086/bilibili_redirect?up_rtmp=txy.live-send.acg.tv%2Flive-txy%2F%3Fstreamname%3Dlive_2335148_7737098%26key%3Dcd7573e18568799dab29522345545aec","provider":"txy"}
         * try_time : 0000-00-00 00:00:00
         */

        private int change;
        private String status;
        private RtmpBean rtmp;
        private String try_time;

        public int getChange() {
            return change;
        }

        public void setChange(int change) {
            this.change = change;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public RtmpBean getRtmp() {
            return rtmp;
        }

        public void setRtmp(RtmpBean rtmp) {
            this.rtmp = rtmp;
        }

        public String getTry_time() {
            return try_time;
        }

        public void setTry_time(String try_time) {
            this.try_time = try_time;
        }

        public static class RtmpBean {
            /**
             * addr : rtmp://txy.live-send.acg.tv/live-txy/
             * code : ?streamname=live_2335148_7737098&key=cd7573e18568799dab29522345545aec
             * new_link : http://tcdns.myqcloud.com:8086/bilibili_redirect?up_rtmp=txy.live-send.acg.tv%2Flive-txy%2F%3Fstreamname%3Dlive_2335148_7737098%26key%3Dcd7573e18568799dab29522345545aec
             * provider : txy
             */

            private String addr;
            private String code;
            private String new_link;
            private String provider;

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

            public String getNew_link() {
                return new_link;
            }

            public void setNew_link(String new_link) {
                this.new_link = new_link;
            }

            public String getProvider() {
                return provider;
            }

            public void setProvider(String provider) {
                this.provider = provider;
            }
        }
    }
}
