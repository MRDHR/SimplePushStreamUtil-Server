package com.dhr.simplepushstreamutil.util;

import com.dhr.simplepushstreamutil.bean.FromClientBean;
import com.google.gson.Gson;
import io.netty.util.internal.StringUtil;
import org.apache.mina.core.session.IoSession;

public class ParseMessageUtil {
    public static final int TYPE_GETFORMATLIST = 1;
    public static final int TYPE_GETM3U8 = 2;
    public static final int TYPE_PUSHSTREAMTOLIVEROOM = 3;
    public static final int TYPE_LOGIN = 4;
    public static final int TYPE_SAVELOGININFO = 5;
    public static final int TYPE_REMOVELOGININFO = 6;
    public static final int TYPE_OPENLIVEROOM = 9;
    private Gson gson;
    private CommandUtil commandUtil;

    public ParseMessageUtil() {
        gson = new Gson();
        commandUtil = new CommandUtil();
    }

    /**
     * 解析数据并分发操作
     *
     * @param message
     */
    public void parse(IoSession session, String message) {
        if (!StringUtil.isNullOrEmpty(message)) {
            FromClientBean fromClientBean = gson.fromJson(message, FromClientBean.class);
            switch (fromClientBean.getType()) {
                case TYPE_GETFORMATLIST:
                    commandUtil.getFormatList(session, fromClientBean);
                    break;
                case TYPE_GETM3U8:
                    commandUtil.getM3u8Url(session, fromClientBean);
                    break;
                case TYPE_PUSHSTREAMTOLIVEROOM:
                    commandUtil.pushStreamToLiveRoom(session, fromClientBean);
                    break;
                case TYPE_LOGIN:
                    commandUtil.login(session, fromClientBean);
                    break;
                case TYPE_SAVELOGININFO:
                    commandUtil.saveLoginInfo(session, fromClientBean);
                    break;
                case TYPE_REMOVELOGININFO:
                    commandUtil.removeLoginInfo(session, fromClientBean);
                    break;
                case TYPE_OPENLIVEROOM:
                    commandUtil.openLiveRoom(session, fromClientBean);
                    break;
            }
        }
    }

}
