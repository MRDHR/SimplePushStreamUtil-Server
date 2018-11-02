package com.hiczp.bilibili.api.web.live.entity;

import java.util.List;

public class LiveInfoEntity {

    /**
     * code : 0
     * msg : 获取成功
     * data : {"achieves":415,"userInfo":{"uid":2335148,"uname":"一生的等待","face":"https://i2.hdslb.com/bfs/face/4fbf831811d08240c0acd80dc904d7012bd82e70.jpg","rank":"10000","identification":1,"mobile_verify":1,"platform_user_level":5,"vip_type":2,"gender":1,"official_verify":{"type":-1,"desc":"","role":0}},"roomid":"12693146","userCoinIfo":{"rcost":162250,"cost":32762800,"vip":"1","svip":"1","vip_time":"2019-08-15 21:22:43","svip_time":"2019-08-09 21:22:43","user_current_score":32762800,"master_level":{"level":5,"current":[450,920],"next":[1180,2100],"color":6406234},"uid":2335148,"uname":"一生的等待","face":"https://i2.hdslb.com/bfs/face/4fbf831811d08240c0acd80dc904d7012bd82e70.jpg","platform_user_level":5,"gold":"16100","silver":"8698","score":1622,"user_level":22,"user_next_level":23,"user_intimacy":6762800,"user_next_intimacy":8000000,"user_level_rank":">50000","bili_coins":0,"coins":78},"vipViewStatus":true,"discount":false,"svip_endtime":"2019-08-09","vip_endtime":"2019-08-15","year_price":233000,"month_price":20000,"action":"index","liveTime":10,"master":{"level":5,"current":702,"next":1180,"medalInfo":null},"san":12,"count":{"guard":1,"fansMedal":13,"title":6,"achieve":0},"sign_anchor":{"status":0,"start_date":"","end_date":""}}
     */

    private int code;
    private String msg;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * achieves : 415
         * userInfo : {"uid":2335148,"uname":"一生的等待","face":"https://i2.hdslb.com/bfs/face/4fbf831811d08240c0acd80dc904d7012bd82e70.jpg","rank":"10000","identification":1,"mobile_verify":1,"platform_user_level":5,"vip_type":2,"gender":1,"official_verify":{"type":-1,"desc":"","role":0}}
         * roomid : 12693146
         * userCoinIfo : {"rcost":162250,"cost":32762800,"vip":"1","svip":"1","vip_time":"2019-08-15 21:22:43","svip_time":"2019-08-09 21:22:43","user_current_score":32762800,"master_level":{"level":5,"current":[450,920],"next":[1180,2100],"color":6406234},"uid":2335148,"uname":"一生的等待","face":"https://i2.hdslb.com/bfs/face/4fbf831811d08240c0acd80dc904d7012bd82e70.jpg","platform_user_level":5,"gold":"16100","silver":"8698","score":1622,"user_level":22,"user_next_level":23,"user_intimacy":6762800,"user_next_intimacy":8000000,"user_level_rank":">50000","bili_coins":0,"coins":78}
         * vipViewStatus : true
         * discount : false
         * svip_endtime : 2019-08-09
         * vip_endtime : 2019-08-15
         * year_price : 233000
         * month_price : 20000
         * action : index
         * liveTime : 10
         * master : {"level":5,"current":702,"next":1180,"medalInfo":null}
         * san : 12
         * count : {"guard":1,"fansMedal":13,"title":6,"achieve":0}
         * sign_anchor : {"status":0,"start_date":"","end_date":""}
         */

        private int achieves;
        private UserInfoBean userInfo;
        private String roomid;
        private UserCoinIfoBean userCoinIfo;
        private boolean vipViewStatus;
        private boolean discount;
        private String svip_endtime;
        private String vip_endtime;
        private int year_price;
        private int month_price;
        private String action;
        private int liveTime;
        private MasterBean master;
        private int san;
        private CountBean count;
        private SignAnchorBean sign_anchor;

        public int getAchieves() {
            return achieves;
        }

        public void setAchieves(int achieves) {
            this.achieves = achieves;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public UserCoinIfoBean getUserCoinIfo() {
            return userCoinIfo;
        }

        public void setUserCoinIfo(UserCoinIfoBean userCoinIfo) {
            this.userCoinIfo = userCoinIfo;
        }

        public boolean isVipViewStatus() {
            return vipViewStatus;
        }

        public void setVipViewStatus(boolean vipViewStatus) {
            this.vipViewStatus = vipViewStatus;
        }

        public boolean isDiscount() {
            return discount;
        }

        public void setDiscount(boolean discount) {
            this.discount = discount;
        }

        public String getSvip_endtime() {
            return svip_endtime;
        }

        public void setSvip_endtime(String svip_endtime) {
            this.svip_endtime = svip_endtime;
        }

        public String getVip_endtime() {
            return vip_endtime;
        }

        public void setVip_endtime(String vip_endtime) {
            this.vip_endtime = vip_endtime;
        }

        public int getYear_price() {
            return year_price;
        }

        public void setYear_price(int year_price) {
            this.year_price = year_price;
        }

        public int getMonth_price() {
            return month_price;
        }

        public void setMonth_price(int month_price) {
            this.month_price = month_price;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getLiveTime() {
            return liveTime;
        }

        public void setLiveTime(int liveTime) {
            this.liveTime = liveTime;
        }

        public MasterBean getMaster() {
            return master;
        }

        public void setMaster(MasterBean master) {
            this.master = master;
        }

        public int getSan() {
            return san;
        }

        public void setSan(int san) {
            this.san = san;
        }

        public CountBean getCount() {
            return count;
        }

        public void setCount(CountBean count) {
            this.count = count;
        }

        public SignAnchorBean getSign_anchor() {
            return sign_anchor;
        }

        public void setSign_anchor(SignAnchorBean sign_anchor) {
            this.sign_anchor = sign_anchor;
        }

        public static class UserInfoBean {
            /**
             * uid : 2335148
             * uname : 一生的等待
             * face : https://i2.hdslb.com/bfs/face/4fbf831811d08240c0acd80dc904d7012bd82e70.jpg
             * rank : 10000
             * identification : 1
             * mobile_verify : 1
             * platform_user_level : 5
             * vip_type : 2
             * gender : 1
             * official_verify : {"type":-1,"desc":"","role":0}
             */

            private int uid;
            private String uname;
            private String face;
            private String rank;
            private int identification;
            private int mobile_verify;
            private int platform_user_level;
            private int vip_type;
            private int gender;
            private OfficialVerifyBean official_verify;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public int getIdentification() {
                return identification;
            }

            public void setIdentification(int identification) {
                this.identification = identification;
            }

            public int getMobile_verify() {
                return mobile_verify;
            }

            public void setMobile_verify(int mobile_verify) {
                this.mobile_verify = mobile_verify;
            }

            public int getPlatform_user_level() {
                return platform_user_level;
            }

            public void setPlatform_user_level(int platform_user_level) {
                this.platform_user_level = platform_user_level;
            }

            public int getVip_type() {
                return vip_type;
            }

            public void setVip_type(int vip_type) {
                this.vip_type = vip_type;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public OfficialVerifyBean getOfficial_verify() {
                return official_verify;
            }

            public void setOfficial_verify(OfficialVerifyBean official_verify) {
                this.official_verify = official_verify;
            }

            public static class OfficialVerifyBean {
                /**
                 * type : -1
                 * desc :
                 * role : 0
                 */

                private int type;
                private String desc;
                private int role;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }
        }

        public static class UserCoinIfoBean {
            /**
             * rcost : 162250
             * cost : 32762800
             * vip : 1
             * svip : 1
             * vip_time : 2019-08-15 21:22:43
             * svip_time : 2019-08-09 21:22:43
             * user_current_score : 32762800
             * master_level : {"level":5,"current":[450,920],"next":[1180,2100],"color":6406234}
             * uid : 2335148
             * uname : 一生的等待
             * face : https://i2.hdslb.com/bfs/face/4fbf831811d08240c0acd80dc904d7012bd82e70.jpg
             * platform_user_level : 5
             * gold : 16100
             * silver : 8698
             * score : 1622
             * user_level : 22
             * user_next_level : 23
             * user_intimacy : 6762800
             * user_next_intimacy : 8000000
             * user_level_rank : >50000
             * bili_coins : 0
             * coins : 78
             */

            private int rcost;
            private int cost;
            private String vip;
            private String svip;
            private String vip_time;
            private String svip_time;
            private int user_current_score;
            private MasterLevelBean master_level;
            private int uid;
            private String uname;
            private String face;
            private int platform_user_level;
            private String gold;
            private String silver;
            private int score;
            private int user_level;
            private int user_next_level;
            private int user_intimacy;
            private int user_next_intimacy;
            private String user_level_rank;
            private String bili_coins;
            private String coins;

            public int getRcost() {
                return rcost;
            }

            public void setRcost(int rcost) {
                this.rcost = rcost;
            }

            public int getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
            }

            public String getVip() {
                return vip;
            }

            public void setVip(String vip) {
                this.vip = vip;
            }

            public String getSvip() {
                return svip;
            }

            public void setSvip(String svip) {
                this.svip = svip;
            }

            public String getVip_time() {
                return vip_time;
            }

            public void setVip_time(String vip_time) {
                this.vip_time = vip_time;
            }

            public String getSvip_time() {
                return svip_time;
            }

            public void setSvip_time(String svip_time) {
                this.svip_time = svip_time;
            }

            public int getUser_current_score() {
                return user_current_score;
            }

            public void setUser_current_score(int user_current_score) {
                this.user_current_score = user_current_score;
            }

            public MasterLevelBean getMaster_level() {
                return master_level;
            }

            public void setMaster_level(MasterLevelBean master_level) {
                this.master_level = master_level;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public int getPlatform_user_level() {
                return platform_user_level;
            }

            public void setPlatform_user_level(int platform_user_level) {
                this.platform_user_level = platform_user_level;
            }

            public String getGold() {
                return gold;
            }

            public void setGold(String gold) {
                this.gold = gold;
            }

            public String getSilver() {
                return silver;
            }

            public void setSilver(String silver) {
                this.silver = silver;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getUser_level() {
                return user_level;
            }

            public void setUser_level(int user_level) {
                this.user_level = user_level;
            }

            public int getUser_next_level() {
                return user_next_level;
            }

            public void setUser_next_level(int user_next_level) {
                this.user_next_level = user_next_level;
            }

            public int getUser_intimacy() {
                return user_intimacy;
            }

            public void setUser_intimacy(int user_intimacy) {
                this.user_intimacy = user_intimacy;
            }

            public int getUser_next_intimacy() {
                return user_next_intimacy;
            }

            public void setUser_next_intimacy(int user_next_intimacy) {
                this.user_next_intimacy = user_next_intimacy;
            }

            public String getUser_level_rank() {
                return user_level_rank;
            }

            public void setUser_level_rank(String user_level_rank) {
                this.user_level_rank = user_level_rank;
            }

            public String getBili_coins() {
                return bili_coins;
            }

            public void setBili_coins(String bili_coins) {
                this.bili_coins = bili_coins;
            }

            public String getCoins() {
                return coins;
            }

            public void setCoins(String coins) {
                this.coins = coins;
            }

            public static class MasterLevelBean {
                /**
                 * level : 5
                 * current : [450,920]
                 * next : [1180,2100]
                 * color : 6406234
                 */

                private int level;
                private int color;
                private List<Integer> current;
                private List<Integer> next;

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getColor() {
                    return color;
                }

                public void setColor(int color) {
                    this.color = color;
                }

                public List<Integer> getCurrent() {
                    return current;
                }

                public void setCurrent(List<Integer> current) {
                    this.current = current;
                }

                public List<Integer> getNext() {
                    return next;
                }

                public void setNext(List<Integer> next) {
                    this.next = next;
                }
            }
        }

        public static class MasterBean {
            /**
             * level : 5
             * current : 702
             * next : 1180
             * medalInfo : null
             */

            private int level;
            private int current;
            private int next;
            private Object medalInfo;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }

            public int getNext() {
                return next;
            }

            public void setNext(int next) {
                this.next = next;
            }

            public Object getMedalInfo() {
                return medalInfo;
            }

            public void setMedalInfo(Object medalInfo) {
                this.medalInfo = medalInfo;
            }
        }

        public static class CountBean {
            /**
             * guard : 1
             * fansMedal : 13
             * title : 6
             * achieve : 0
             */

            private int guard;
            private int fansMedal;
            private int title;
            private int achieve;

            public int getGuard() {
                return guard;
            }

            public void setGuard(int guard) {
                this.guard = guard;
            }

            public int getFansMedal() {
                return fansMedal;
            }

            public void setFansMedal(int fansMedal) {
                this.fansMedal = fansMedal;
            }

            public int getTitle() {
                return title;
            }

            public void setTitle(int title) {
                this.title = title;
            }

            public int getAchieve() {
                return achieve;
            }

            public void setAchieve(int achieve) {
                this.achieve = achieve;
            }
        }

        public static class SignAnchorBean {
            /**
             * status : 0
             * start_date :
             * end_date :
             */

            private int status;
            private String start_date;
            private String end_date;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }
        }
    }
}
