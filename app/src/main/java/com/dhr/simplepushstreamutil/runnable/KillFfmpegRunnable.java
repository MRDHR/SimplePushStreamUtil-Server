package com.dhr.simplepushstreamutil.runnable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillFfmpegRunnable implements Runnable {
    private final InputStream istrm_;

    public KillFfmpegRunnable(InputStream istrm) {
        istrm_ = istrm;
    }

    @Override
    public void run() {
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(istrm_, "GBK"));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
    }

}