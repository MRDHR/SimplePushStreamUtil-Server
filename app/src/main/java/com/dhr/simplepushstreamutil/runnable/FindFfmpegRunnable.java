package com.dhr.simplepushstreamutil.runnable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FindFfmpegRunnable implements Runnable {
    private final InputStream istrm_;
    private FindFfmpegCallBack findFfmpegCallBack;

    public FindFfmpegRunnable(InputStream istrm, FindFfmpegCallBack findFfmpegCallBack) {
        istrm_ = istrm;
        this.findFfmpegCallBack = findFfmpegCallBack;
    }

    @Override
    public void run() {
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(istrm_, "GBK"));
            List<String> result = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                result.add(line);
            }
            findFfmpegCallBack.findFfmpegSuccess(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
    }

    public interface FindFfmpegCallBack {
        void findFfmpegSuccess(List<String> result);
    }

}