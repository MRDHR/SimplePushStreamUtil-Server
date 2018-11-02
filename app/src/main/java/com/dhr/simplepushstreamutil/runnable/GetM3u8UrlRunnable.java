package com.dhr.simplepushstreamutil.runnable;

import com.dhr.simplepushstreamutil.bean.ResolutionBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetM3u8UrlRunnable implements Runnable {
    private final InputStream istrm_;
    private GetM3u8UrlCallBack getM3u8UrlCallBack;
    private List<ResolutionBean> listResolutions = new ArrayList<>();
    private List<String> errLog = new ArrayList<>();
    private String m3u8Url;

    public GetM3u8UrlRunnable(InputStream istrm, GetM3u8UrlCallBack getM3u8UrlCallBack) {
        istrm_ = istrm;
        this.getM3u8UrlCallBack = getM3u8UrlCallBack;
    }

    @Override
    public void run() {
        try {
            String line = "";
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(istrm_, "GBK"));
            boolean startResolutions = false;
            listResolutions.clear();
            errLog.clear();
            boolean hasDataReturn = false;
            while ((line = bufferedReader.readLine()) != null) {
                hasDataReturn = true;
                System.out.println(line);
                errLog.add(line);
                if (line.startsWith("http")) {
                    m3u8Url = line;
                    break;
                }
            }
            if (hasDataReturn) {
                if (!m3u8Url.isEmpty()) {
                    getM3u8UrlCallBack.GetM3u8UrlSuccess(m3u8Url);
                } else {
                    getM3u8UrlCallBack.GetM3u8UrlFail(errLog);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
    }

    public interface GetM3u8UrlCallBack {
        void GetM3u8UrlSuccess(String m3u8Url);

        void GetM3u8UrlFail(List<String> errLog);
    }

}
