package com.dhr.simplepushstreamutil.runnable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class OpenPortRunnable implements Runnable {
    private static final Logger LOG = Logger.getLogger("system");
    private final InputStream istrm_;

    public OpenPortRunnable(InputStream istrm) {
        istrm_ = istrm;
    }

    @Override
    public void run() {
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(istrm_, "GBK"));
            while ((line = bufferedReader.readLine()) != null) {
                LOG.info(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
    }
}