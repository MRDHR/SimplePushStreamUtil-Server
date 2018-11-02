package com.dhr.simplepushstreamutil.runnable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PushStreamRunnable implements Runnable {
    private final InputStream istrm_;
    private PushStreamCallBack pushStreamCallBack;

    public PushStreamRunnable(InputStream istrm, PushStreamCallBack pushStreamCallBack) {
        istrm_ = istrm;
        this.pushStreamCallBack = pushStreamCallBack;
    }

    @Override
    public void run() {
        try {
            String line = null;
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(istrm_, "GBK"));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String trim = line.replaceAll(" ", "").toLowerCase().trim();
                if (trim.startsWith("frame") || trim.startsWith("size")) {
                    String size;
                    String time;
                    String bitrate;
                    int sizeStartIndex = trim.indexOf("size=");
                    int sizeEndTimeStartIndex = trim.indexOf("kbtime=");
                    int timeEndBitrateStartIndex = trim.indexOf("bitrate=");
                    int BitrateENDIndex = trim.indexOf("kbits/s");
                    size = trim.substring(sizeStartIndex, sizeEndTimeStartIndex);
                    time = trim.substring(sizeEndTimeStartIndex, timeEndBitrateStartIndex);
                    bitrate = trim.substring(timeEndBitrateStartIndex, BitrateENDIndex);
                    pushStreamCallBack.pushing(size.replaceAll("size=", ""), time.replaceAll("kbtime=", ""), bitrate.replaceAll("bitrate=", ""));
                } else if (line.toLowerCase().contains("error") || line.toLowerCase().contains("command not found")) {
                    pushStreamCallBack.pushFail(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
    }

    public interface PushStreamCallBack {
        void pushing(String size, String time, String bitrate);

        void pushFail(String reason);
    }

}
