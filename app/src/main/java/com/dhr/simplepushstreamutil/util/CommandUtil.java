package com.dhr.simplepushstreamutil.util;

import com.dhr.simplepushstreamutil.bean.FromClientBean;
import com.dhr.simplepushstreamutil.bean.FromServerBean;
import com.dhr.simplepushstreamutil.bean.LocalDataBean;
import com.dhr.simplepushstreamutil.bean.ResolutionBean;
import com.dhr.simplepushstreamutil.mina.MinaServer;
import com.dhr.simplepushstreamutil.runnable.*;
import com.google.gson.Gson;
import com.hiczp.bilibili.api.BilibiliAPI;
import com.hiczp.bilibili.api.BilibiliAccount;
import com.hiczp.bilibili.api.passport.entity.LoginResponseEntity;
import com.hiczp.bilibili.api.passport.exception.CaptchaMismatchException;
import com.hiczp.bilibili.api.web.BilibiliWebAPI;
import com.hiczp.bilibili.api.web.live.LiveService;
import com.hiczp.bilibili.api.web.live.entity.*;
import io.netty.util.internal.StringUtil;
import okhttp3.Cookie;
import org.apache.mina.core.session.IoSession;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandUtil {
    private Gson gson;
    private IoSession session;
    private Process process = null;
    private PrintWriter stdin;
    private ExecutorService executorService;
    private SimpleDateFormat simpleDateFormat;
    private JsonUtil jsonUtil;

    private LocalDataBean localDataBean;
    private BilibiliAccount bilibiliAccount;
    private BilibiliAPI bilibiliAPI;
    private BilibiliWebAPI bilibiliWebAPI;
    private Map<String, List<Cookie>> cookiesMap;
    private LiveService liveService;
    private String roomId;
    private String csrfToken = "";
    private StartLiveEntity.DataBean.RtmpBean rtmp;

    public CommandUtil(IoSession session) {
        this.session = session;
        gson = new Gson();
        executorService = Executors.newCachedThreadPool();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonUtil = new JsonUtil();

        String localData = jsonUtil.getDatafromFile(LocalDataBean.class.getSimpleName());
        if (StringUtil.isNullOrEmpty(localData)) {
            localDataBean = new LocalDataBean();
            jsonUtil.saveDataToFile(LocalDataBean.class.getSimpleName(), gson.toJson(localDataBean));
        } else {
            localDataBean = gson.fromJson(localData, LocalDataBean.class);
        }
    }

    /**
     * 开启端口
     */
    public void openPort() {
        executorService.execute(() -> {
            Runtime run = Runtime.getRuntime();
            File wd = new File("/bin");
            try {
                process = run.exec("/bin/bash", null, wd);
                if (null != process) {
                    stdin = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"), true);
                    new Thread(new OpenPortRunnable(process.getErrorStream())).start();
                    new Thread(new OpenPortRunnable(process.getInputStream())).start();
                    stdin.println("firewall-cmd --add-port=" + MinaServer.PORT + "/tcp --permanent");
                    stdin.println("firewall-cmd --reload");
                    stdin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取分辨率列表
     *
     * @param fromClientBean
     */
    public void getFormatList(FromClientBean fromClientBean) {
        executorService.execute(() -> {
            Runtime run = Runtime.getRuntime();
            File wd = new File("/bin");
            try {
                process = run.exec("/bin/bash", null, wd);
                if (null != process) {
                    stdin = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"), true);
                    new Thread(new GetFormatListRunnable(process.getErrorStream(), getFormatListCallBack, fromClientBean.getSchemeType())).start();
                    new Thread(new GetFormatListRunnable(process.getInputStream(), getFormatListCallBack, fromClientBean.getSchemeType())).start();
                    stdin.println(fromClientBean.getCmd());
                    stdin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取分辨率列表的回调
     */
    private GetFormatListRunnable.GetFormatListCallBack getFormatListCallBack = new GetFormatListRunnable.GetFormatListCallBack() {
        @Override
        public void getFormatListSuccess(List<ResolutionBean> listResolutions) {
            String result = gson.toJson(listResolutions);
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setResult(result);
            fromServerBean.setType(ParseMessageUtil.TYPE_GETFORMATLIST);
            fromServerBean.setCode(0);
            session.write(fromServerBean);
            System.out.println(fromServerBean.toString());
        }

        @Override
        public void getFormatListFail(List<String> errLog) {
            FromServerBean fromServerBean = new FromServerBean();
            StringBuilder stringBuilder = new StringBuilder("\n\n获取分辨率列表失败");
            for (String str : errLog) {
                stringBuilder.append(str).append("\n");
            }
            fromServerBean.setResult(stringBuilder.toString());
            fromServerBean.setType(ParseMessageUtil.TYPE_GETFORMATLIST);
            fromServerBean.setCode(1);
            session.write(fromServerBean);
            System.out.println(fromServerBean.toString());
        }
    };

    public void getM3u8Url(FromClientBean fromClientBean) {
        executorService.execute(() -> {
            Runtime run = Runtime.getRuntime();
            File wd = new File("/bin");
            try {
                process = run.exec("/bin/bash", null, wd);
                if (null != process) {
                    stdin = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"), true);
                    new Thread(new GetM3u8UrlRunnable(process.getErrorStream(), getM3u8UrlCallBack)).start();
                    new Thread(new GetM3u8UrlRunnable(process.getInputStream(), getM3u8UrlCallBack)).start();
                    stdin.println(fromClientBean.getCmd());
                    stdin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取m3u8地址的回调
     */
    private GetM3u8UrlRunnable.GetM3u8UrlCallBack getM3u8UrlCallBack = new GetM3u8UrlRunnable.GetM3u8UrlCallBack() {
        @Override
        public void GetM3u8UrlSuccess(String m3u8Url) {
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setResult(m3u8Url);
            fromServerBean.setType(ParseMessageUtil.TYPE_GETM3U8);
            fromServerBean.setCode(0);
            session.write(fromServerBean);
        }

        @Override
        public void GetM3u8UrlFail(List<String> errLog) {
            FromServerBean fromServerBean = new FromServerBean();
            StringBuilder stringBuilder = new StringBuilder("\n\n获取直播源失败");
            for (String str : errLog) {
                stringBuilder.append(str).append("\n");
            }
            fromServerBean.setResult(stringBuilder.toString());
            fromServerBean.setType(ParseMessageUtil.TYPE_GETM3U8);
            fromServerBean.setCode(1);
            session.write(fromServerBean);
        }
    };


    public void pushStreamToLiveRoom(FromClientBean fromClientBean) {
        executorService.execute(() -> {
            Runtime run = Runtime.getRuntime();
            File wd = new File("/bin");
            try {
                process = run.exec("/bin/bash", null, wd);
                if (null != process) {
                    stdin = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"), true);
                    new Thread(new PushStreamRunnable(process.getErrorStream(), pushStreamCallBack)).start();
                    new Thread(new PushStreamRunnable(process.getInputStream(), pushStreamCallBack)).start();
                    stdin.println(fromClientBean.getCmd());
                    stdin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 推流的回调
     */
    private PushStreamRunnable.PushStreamCallBack pushStreamCallBack = new PushStreamRunnable.PushStreamCallBack() {
        @Override
        public void pushing(String size, String time, String bitrate) {
            BigDecimal bigDecimal = new BigDecimal(size);
            int i = bigDecimal.intValue();
            String result1;
            if (i >= 1000) {
                result1 = bigDecimal.divide(new BigDecimal(1024)).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "MB";
            } else {
                result1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "KB";
            }
            String result2 = new BigDecimal(bitrate).divide(new BigDecimal(8)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setResult("\n\n" + simpleDateFormat.format(new Date()) + "\n" + "已推送文件大小：" + result1 + "\u3000已推流时长："
                    + time.substring(0, time.lastIndexOf(".")) + "\u3000上传速度：" + result2 + "KB/S");
            fromServerBean.setType(ParseMessageUtil.TYPE_PUSHSTREAMTOLIVEROOM);
            fromServerBean.setCode(0);
            session.write(fromServerBean);
        }

        @Override
        public void pushFail(String reason) {
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setResult("\n\n" + reason);
            fromServerBean.setType(ParseMessageUtil.TYPE_PUSHSTREAMTOLIVEROOM);
            fromServerBean.setCode(1);
            session.write(fromServerBean);
        }
    };

    public void login(FromClientBean fromClientBean) {
        executorService.execute(() -> {
            BilibiliAPI bilibiliAPI = new BilibiliAPI();
            try {
                String userName = fromClientBean.getUserName();
                String password = fromClientBean.getPassword();
                LoginResponseEntity loginResponseEntity = bilibiliAPI.login(userName, password);
                int code = loginResponseEntity.getCode();
                if (0 == code) {
                    bilibiliAccount = loginResponseEntity.toBilibiliAccount();
                    FromServerBean fromServerBean = new FromServerBean();
                    fromServerBean.setType(ParseMessageUtil.TYPE_LOGIN);
                    fromServerBean.setCode(0);
                    fromServerBean.setResult("登录成功");
                    session.write(fromServerBean);
                }
            } catch (IOException e) {
                e.printStackTrace();
                FromServerBean fromServerBean = new FromServerBean();
                fromServerBean.setType(ParseMessageUtil.TYPE_LOGIN);
                fromServerBean.setCode(1);
                fromServerBean.setResult("网络异常");
                session.write(fromServerBean);
            } catch (LoginException e) {
                e.printStackTrace();
                FromServerBean fromServerBean = new FromServerBean();
                fromServerBean.setType(ParseMessageUtil.TYPE_LOGIN);
                fromServerBean.setCode(1);
                fromServerBean.setResult("用户名密码错误，请重新输入后再试");
                session.write(fromServerBean);
            } catch (CaptchaMismatchException e) {
                e.printStackTrace();
                FromServerBean fromServerBean = new FromServerBean();
                fromServerBean.setType(ParseMessageUtil.TYPE_LOGIN);
                fromServerBean.setCode(1);
                fromServerBean.setResult("需要输入验证码，请联系本人");
                session.write(fromServerBean);
            }
        });
    }

    public void saveLoginInfo(FromClientBean fromClientBean) {
        if (null == bilibiliAccount) {
            FromServerBean fromServerBean = new FromServerBean();
            fromClientBean.setType(ParseMessageUtil.TYPE_SAVELOGININFO);
            fromServerBean.setCode(1);
            fromServerBean.setResult("尚未进行登录，请登录后重试");
            session.write(fromServerBean);
        } else {
            localDataBean.setBilibiliAccount(bilibiliAccount);
            jsonUtil.saveDataToFile(LocalDataBean.class.getSimpleName(), gson.toJson(localDataBean));
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setType(ParseMessageUtil.TYPE_SAVELOGININFO);
            fromServerBean.setCode(0);
            fromServerBean.setResult("登录信息保存成功");
            session.write(fromServerBean);
        }
    }

    public void removeLoginInfo() {
        bilibiliAccount = new BilibiliAccount("", "", 0L, 0L, 0L);
        localDataBean.setBilibiliAccount(bilibiliAccount);
        jsonUtil.saveDataToFile(LocalDataBean.class.getSimpleName(), gson.toJson(localDataBean));
        FromServerBean fromServerBean = new FromServerBean();
        fromServerBean.setType(ParseMessageUtil.TYPE_REMOVELOGININFO);
        fromServerBean.setCode(0);
        fromServerBean.setResult("登录信息删除成功");
        session.write(fromServerBean);
    }

    /**
     * 获取分区列表
     */
    private void getAreaList() {
        executorService.execute(() -> {
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setType(ParseMessageUtil.TYPE_GETAREALIST);
            fromServerBean.setCode(1);
            fromServerBean.setResult("\n\n开始获取分区列表信息，请稍候...");
            session.write(fromServerBean);

            bilibiliAPI = new BilibiliAPI(bilibiliAccount);
            try {
                cookiesMap = bilibiliAPI.toCookies();
                bilibiliWebAPI = new BilibiliWebAPI(cookiesMap);
                liveService = bilibiliWebAPI.getLiveService();
                LiveAreaListEntity liveAreaListEntity = liveService.getLiveAreaList().execute().body();
                if (null != liveAreaListEntity && 0 == liveAreaListEntity.getCode()) {
                    fromServerBean = new FromServerBean();
                    fromServerBean.setType(ParseMessageUtil.TYPE_GETAREALIST);
                    fromServerBean.setCode(1);
                    fromServerBean.setResult("\n\n获取分区列表信息成功");
                    session.write(fromServerBean);
                    fromServerBean = new FromServerBean();
                    fromServerBean.setType(ParseMessageUtil.TYPE_GETAREALIST);
                    fromServerBean.setCode(0);
                    fromServerBean.setResult(gson.toJson(liveAreaListEntity.getData()));
                    session.write(fromServerBean);
                } else {
                    fromServerBean = new FromServerBean();
                    fromServerBean.setType(ParseMessageUtil.TYPE_GETAREALIST);
                    fromServerBean.setCode(1);
                    fromServerBean.setResult("\n\n获取分区列表失败，请稍后再试");
                    session.write(fromServerBean);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    public void updateTitleAndOpenLiveRoom(FromClientBean fromClientBean) {
        executorService.execute(() -> {
            try {
                FromServerBean fromServerBean = new FromServerBean();
                fromServerBean.setType(ParseMessageUtil.TYPE_UPDATETITLEANDOPENLIVEROOM);
                fromServerBean.setCode(1);
                fromServerBean.setResult("\n\n开始获取开启直播相关参数，请稍候...");
                session.write(fromServerBean);
                LiveInfoEntity liveInfoEntity = liveService.getLiveInfo().execute().body();
                if (null != liveInfoEntity && 0 == liveInfoEntity.getCode()) {
                    roomId = liveInfoEntity.getData().getRoomid();
                    List<Cookie> cookies = cookiesMap.get("bilibili.com");
                    for (Cookie cookie : cookies) {
                        if ("bili_jct".equals(cookie.name())) {
                            csrfToken = cookie.value();
                            break;
                        }
                    }
                    fromServerBean = new FromServerBean();
                    fromServerBean.setType(ParseMessageUtil.TYPE_UPDATETITLEANDOPENLIVEROOM);
                    fromServerBean.setCode(1);
                    fromServerBean.setResult("\n\n获取开启直播参数成功，开始修改房间标题...");
                    session.write(fromServerBean);
                    UpdateRoomTitleEntity updateRoomTitleEntity = liveService.updateRoomTitle(
                            roomId, fromClientBean.getRoomName(), csrfToken).execute().body();
                    if (null != updateRoomTitleEntity && 0 == updateRoomTitleEntity.getCode()) {
                        fromServerBean = new FromServerBean();
                        fromServerBean.setType(ParseMessageUtil.TYPE_UPDATETITLEANDOPENLIVEROOM);
                        fromServerBean.setCode(1);
                        fromServerBean.setResult("\n\n修改房间标题成功，正在开启直播...");
                        session.write(fromServerBean);
                        StartLiveEntity startLiveEntity = liveService.startLive(
                                roomId, "pc", fromClientBean.getAreaId(), csrfToken).execute().body();
                        if (null != startLiveEntity && 0 == startLiveEntity.getCode()) {
                            fromServerBean = new FromServerBean();
                            fromServerBean.setType(ParseMessageUtil.TYPE_UPDATETITLEANDOPENLIVEROOM);
                            fromServerBean.setCode(0);
                            fromServerBean.setResult("\n\n开启直播成功");
                            session.write(fromServerBean);
                            rtmp = startLiveEntity.getData().getRtmp();
                            //打开直播成功
                        } else {
                            fromServerBean = new FromServerBean();
                            fromServerBean.setType(ParseMessageUtil.TYPE_UPDATETITLEANDOPENLIVEROOM);
                            fromServerBean.setCode(1);
                            fromServerBean.setResult("\n\n开启直播失败，请稍后再试");
                            session.write(fromServerBean);
                        }
                    } else {
                        fromServerBean = new FromServerBean();
                        fromServerBean.setType(ParseMessageUtil.TYPE_UPDATETITLEANDOPENLIVEROOM);
                        fromServerBean.setCode(1);
                        fromServerBean.setResult("\n\n更新房间标题失败，请稍后再试");
                        session.write(fromServerBean);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    public void openLiveRoom() {
        BilibiliAccount bilibiliAccount = localDataBean.getBilibiliAccount();
        if (null == bilibiliAccount || null == bilibiliAccount.getAccessToken() || bilibiliAccount.getAccessToken().isEmpty()) {
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setType(ParseMessageUtil.TYPE_OPENLIVEROOM);
            fromServerBean.setCode(1);
            fromServerBean.setResult("登录信息为空，请在菜单栏中登录B站账号信息后重试");
            session.write(fromServerBean);
        } else {
            getAreaList();
        }
    }

    public void closeLiveRoom() {
        BilibiliAccount bilibiliAccount = localDataBean.getBilibiliAccount();
        if (null == bilibiliAccount || null == bilibiliAccount.getAccessToken() || bilibiliAccount.getAccessToken().isEmpty()) {
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setType(ParseMessageUtil.TYPE_CLOSELIVEROOM);
            fromServerBean.setCode(1);
            fromServerBean.setResult("登录信息为空，请在菜单栏中登录B站账号信息后重试");
            session.write(fromServerBean);
        } else {
            try {
                StopLiveEntity stopLiveEntity = liveService.stopLive(roomId, "pc", csrfToken).execute().body();
                if (null != stopLiveEntity && 0 == stopLiveEntity.getCode()) {
                    FromServerBean fromServerBean = new FromServerBean();
                    fromServerBean.setType(ParseMessageUtil.TYPE_CLOSELIVEROOM);
                    fromServerBean.setCode(0);
                    fromServerBean.setResult("\n\n关闭直播间成功");
                    session.write(fromServerBean);
                } else {
                    FromServerBean fromServerBean = new FromServerBean();
                    fromServerBean.setType(ParseMessageUtil.TYPE_CLOSELIVEROOM);
                    fromServerBean.setCode(1);
                    fromServerBean.setResult("\n\n关闭直播间失败，请自行去B站停止直播");
                    session.write(fromServerBean);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void toLiveRoom() {
        if (null != roomId && !roomId.isEmpty()) {
            String site = "https://live.bilibili.com/" + roomId;
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setType(ParseMessageUtil.TYPE_TOMYLIVEROOM);
            fromServerBean.setCode(0);
            fromServerBean.setResult(site);
            session.write(fromServerBean);
        } else {
            FromServerBean fromServerBean = new FromServerBean();
            fromServerBean.setType(ParseMessageUtil.TYPE_TOMYLIVEROOM);
            fromServerBean.setCode(1);
            fromServerBean.setResult("\n\n未打开直播间，无法跳转");
            session.write(fromServerBean);
        }
    }

    public void liveRoomIsOpen() {
        String liveRoomUrl = rtmp.getAddr() + rtmp.getCode();
        FromServerBean fromServerBean = new FromServerBean();
        fromServerBean.setType(ParseMessageUtil.TYPE_LIVEROOMISOPEN);
        if (liveRoomUrl.isEmpty()) {
            fromServerBean.setCode(1);
            fromServerBean.setResult("请先打开直播间");
        } else {
            fromServerBean.setCode(0);
            fromServerBean.setResult(liveRoomUrl);
        }
        session.write(fromServerBean);
    }

    public void stopPushStream(FromClientBean fromClientBean) {
        executorService.execute(() -> {
            Runtime run = Runtime.getRuntime();
            File wd = new File("/bin");
            try {
                process = run.exec("/bin/bash", null, wd);
                if (null != process) {
                    stdin = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"), true);
                    new Thread(new FindFfmpegRunnable(process.getErrorStream(), findFfmpegCallBack)).start();
                    new Thread(new FindFfmpegRunnable(process.getInputStream(), findFfmpegCallBack)).start();
                    stdin.println(fromClientBean.getCmd());
                    stdin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private FindFfmpegRunnable.FindFfmpegCallBack findFfmpegCallBack = new FindFfmpegRunnable.FindFfmpegCallBack() {
        @Override
        public void findFfmpegSuccess(List<String> result) {
            for (String str : result) {
                Runtime run = Runtime.getRuntime();
                File wd = new File("/bin");
                try {
                    process = run.exec("/bin/bash", null, wd);
                    if (null != process) {
                        stdin = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"), true);
                        new Thread(new KillFfmpegRunnable(process.getErrorStream())).start();
                        new Thread(new KillFfmpegRunnable(process.getInputStream())).start();
                        stdin.println("kill -9 " + str);
                        stdin.close();
                        FromServerBean fromServerBean = new FromServerBean();
                        fromServerBean.setType(ParseMessageUtil.TYPE_STOPPUSHSTREAM);
                        fromServerBean.setCode(0);
                        fromServerBean.setResult("\n\n结束推流成功");
                        session.write(fromServerBean);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
