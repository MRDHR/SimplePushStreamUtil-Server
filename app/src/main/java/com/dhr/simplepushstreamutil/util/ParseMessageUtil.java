package com.dhr.simplepushstreamutil.util;

import com.dhr.simplepushstreamutil.bean.FromClientBean;
import org.apache.mina.core.session.IoSession;

public class ParseMessageUtil {
    public static final int TYPE_OPENPORT = 0;
    public static final int TYPE_GETFORMATLIST = 1;
    public static final int TYPE_GETM3U8 = 2;
    public static final int TYPE_PUSHSTREAMTOLIVEROOM = 3;
    public static final int TYPE_LOGIN = 4;
    public static final int TYPE_SAVELOGININFO = 5;
    public static final int TYPE_REMOVELOGININFO = 6;
    public static final int TYPE_GETAREALIST = 7;
    public static final int TYPE_UPDATETITLEANDOPENLIVEROOM = 8;
    public static final int TYPE_OPENLIVEROOM = 9;
    public static final int TYPE_CLOSELIVEROOM = 10;
    public static final int TYPE_TOMYLIVEROOM = 11;
    public static final int TYPE_LIVEROOMISOPEN = 12;
    public static final int TYPE_STOPPUSHSTREAM = 13;
    private CommandUtil commandUtil;

    public ParseMessageUtil() {
        commandUtil = new CommandUtil();
    }

    /**
     * 解析数据并分发操作
     *
     * @param fromClientBean
     */
    public void parse(FromClientBean fromClientBean) {
        if (null != fromClientBean) {
            switch (fromClientBean.getType()) {
                case TYPE_OPENPORT:
                    commandUtil.openPort();
                    break;
                case TYPE_GETFORMATLIST:
                    commandUtil.getFormatList(fromClientBean);
                    break;
                case TYPE_GETM3U8:
                    commandUtil.getM3u8Url(fromClientBean);
                    break;
                case TYPE_PUSHSTREAMTOLIVEROOM:
                    commandUtil.pushStreamToLiveRoom(fromClientBean);
                    break;
                case TYPE_LOGIN:
                    commandUtil.login(fromClientBean);
                    break;
                case TYPE_SAVELOGININFO:
                    commandUtil.saveLoginInfo(fromClientBean);
                    break;
                case TYPE_REMOVELOGININFO:
                    commandUtil.removeLoginInfo();
                    break;
                case TYPE_UPDATETITLEANDOPENLIVEROOM:
                    commandUtil.updateTitleAndOpenLiveRoom(fromClientBean);
                    break;
                case TYPE_OPENLIVEROOM:
                    commandUtil.openLiveRoom();
                    break;
                case TYPE_CLOSELIVEROOM:
                    commandUtil.closeLiveRoom();
                    break;
                case TYPE_TOMYLIVEROOM:
                    commandUtil.toLiveRoom();
                    break;
                case TYPE_LIVEROOMISOPEN:
                    commandUtil.liveRoomIsOpen();
                    break;
                case TYPE_STOPPUSHSTREAM:
                    commandUtil.stopPushStream(fromClientBean);
                    break;
            }
        }
    }

    /**
     * 解析数据并分发操作
     *
     * @param fromClientBean
     */
    public void parse(IoSession session, FromClientBean fromClientBean) {
        commandUtil.updateSession(session);
        parse(fromClientBean);
    }

    public void updateSession(IoSession session) {
        commandUtil.updateSession(session);
    }

}
