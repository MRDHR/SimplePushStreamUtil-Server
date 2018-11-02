package com.dhr.simplepushstreamutil.runnable;

import com.dhr.simplepushstreamutil.bean.ResolutionBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetFormatListRunnable implements Runnable {
    private final InputStream istrm_;
    private GetFormatListCallBack getFormatListCallBack;
    private List<ResolutionBean> listResolutions = new ArrayList<>();
    private List<String> errLog = new ArrayList<>();
    private int schemeType;

    public GetFormatListRunnable(InputStream istrm, GetFormatListCallBack getFormatListCallBack, int schemeType) {
        istrm_ = istrm;
        this.getFormatListCallBack = getFormatListCallBack;
        this.schemeType = schemeType;
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
                if (0 == schemeType) {
                    if ("format code  extension  resolution note".equals(line)) {
                        startResolutions = true;
                        continue;
                    }
                    if (startResolutions) {
                        String regEx = "[0-9]\\d{2,4}x[0-9]\\d{2,4}";
                        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(line);
                        boolean rs = matcher.find();

                        String regEx1 = "([1-9]\\d*\\.?\\d*)fps";
                        Pattern pattern1 = Pattern.compile(regEx1, Pattern.CASE_INSENSITIVE);
                        Matcher matcher1 = pattern1.matcher(line);
                        boolean rs1 = matcher1.find();
                        if (rs) {
                            String s = matcher.group();
                            ResolutionBean resolutionBean = new ResolutionBean();
                            resolutionBean.setResolutionPx(s);
                            String[] s1 = line.split(" ");
                            if (s1.length > 0) {
                                String s2 = s1[0];
                                String trim = s2.replaceAll(" ", "").toLowerCase().trim();
                                resolutionBean.setResolutionNo(trim);
                            }

                            if (rs1) {
                                s = matcher1.group();
                                resolutionBean.setFps(s);
                            }
                            listResolutions.add(resolutionBean);
                        }
                    }
                } else if (1 == schemeType) {
                    if (line.contains("Available streams")) {
                        startResolutions = true;
                        line = line.replace("Available streams:", "");
                        String[] split = line.split(",");
                        if (0 < split.length) {
                            for (String str : split) {
                                ResolutionBean resolutionBean = new ResolutionBean();
                                resolutionBean.setResolutionPx(str);
                                listResolutions.add(resolutionBean);
                            }
                        }
                    }
                }
            }
            if (hasDataReturn) {
                if (startResolutions) {
                    getFormatListCallBack.getFormatListSuccess(listResolutions);
                } else {
                    getFormatListCallBack.getFormatListFail(errLog);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
    }


    public interface GetFormatListCallBack {
        void getFormatListSuccess(List<ResolutionBean> listResolutions);

        void getFormatListFail(List<String> errLog);
    }
}
