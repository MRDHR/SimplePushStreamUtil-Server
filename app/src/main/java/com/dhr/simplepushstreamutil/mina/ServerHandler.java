package com.dhr.simplepushstreamutil.mina;


import com.dhr.simplepushstreamutil.bean.FromClientBean;
import com.dhr.simplepushstreamutil.bean.FromServerBean;
import com.dhr.simplepushstreamutil.util.ParseMessageUtil;
import com.google.gson.Gson;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;
import java.util.logging.Logger;

public class ServerHandler extends IoHandlerAdapter {
    private static final Logger LOG = Logger.getLogger("system");
    private ParseMessageUtil parseMessageUtil;

    ServerHandler() {
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        parseMessageUtil = new ParseMessageUtil(session);
        FromClientBean fromClientBean = new FromClientBean();
        fromClientBean.setType(0);
        parseMessageUtil.parse(fromClientBean);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        /**
         * 自定义异常处理， 要不然异常会被“吃掉”；
         */
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        /**
         * 对接收到的消息（已经解码）迕行下一步处理，这里对收到的字符串进行判断，
         * 如果是”quit”则断开连接；否则输出当前时间的字符串格式；
         */
//        String str = message.toString();
//        if (str.trim().equalsIgnoreCase("quit")) {
//            session.closeNow();
//            return;
//        }
        FromClientBean fromClientBean = (FromClientBean) message;
        LOG.info("服务器接收到的数据：" + fromClientBean.toString());
        parseMessageUtil.parse(fromClientBean);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        LOG.info("客户端与服务端断开连接.....");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        /**
         * 当Session处于IDLE状态的时候，输出空闲状态次数；
         */
        LOG.info("IDLE:" + session.getIdleCount(status));
    }

}