package com.dhr.simplepushstreamutil.mina;


import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoEventType;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * @author aniyo
 * blog: http://aniyo.iteye.com
 */
public class MinaServer {
    /**
     * 监听的端口
     */
    public static final int PORT = 9123;
    private static final Logger LOG = Logger.getLogger("system");

    private static void start() throws IOException {
        LOG.info("[##########业务服务器监听初始化中......##########]");
        //创建一个非阻塞的server端的Socket来接受请求,执行读写操作的线程数设置为CPU核心数量+1
        NioSocketAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() * 2);
        //设置编码解码器
        //创建接收数据的过滤器
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        //设定这个过滤器将以对象为单位读取数据
        ProtocolCodecFilter filter= new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
        chain.addLast("objectFilter",filter);

        //设置Message received处理线程
        ExecutorService executor = Executors.newFixedThreadPool(50);
        acceptor.getFilterChain().addLast("executor",
                new ExecutorFilter(executor, IoEventType.MESSAGE_RECEIVED));
        //会话开启处理线程池 - 对应句柄中的sessionOpened
        acceptor.getFilterChain().addLast("SessionOpenedThreadPool",
                new ExecutorFilter(Executors.newCachedThreadPool(), IoEventType.SESSION_OPENED));
        //会话建立时处理线程池
        acceptor.getFilterChain().addLast("SessionCreatedThreadPool",
                new ExecutorFilter(Executors.newCachedThreadPool(), IoEventType.WRITE));
        //设置句柄,业务处理，接收Client发来的请求，并发送处理完的数据到客户端
        acceptor.setHandler(new ServerHandler());
        // 读取数据的缓冲区大小
        acceptor.getSessionConfig().setReadBufferSize(1024 * 150);
        acceptor.getSessionConfig().setSendBufferSize(1024 * 150);
        // 读写通道均在40 秒内无任何操作就进入空闲状态
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
        acceptor.setReuseAddress(true);
        acceptor.setCloseOnDeactivation(false);
        try {
            acceptor.bind(new InetSocketAddress(PORT));
            LOG.info("[##########业务服务器监听已启动,ON PORT:" + PORT + "##########]");
            acceptor.isActive();
        } catch (IOException e) {
            e.printStackTrace();
            LOG.info("业务服务器监听程序启动失败");
        }
    }

    public static void main(String[] args) {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}