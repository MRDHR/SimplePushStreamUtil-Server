package com.dhr.simplepushstreamutil.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonUtil {
    public void saveDataToFile(String fileName, String data) {
        BufferedWriter writer = null;
        File file = new File(System.getProperty("user.dir") + "/" + fileName + ".json");
        //如果文件不存在，则新建一个
        if (!file.exists()) {
            try {
                file.createNewFile();
                Runtime run = Runtime.getRuntime();
                File wd = new File("/bin");
                try {
                    Process process = run.exec("/bin/bash", null, wd);
                    if (null != process) {
                        PrintWriter stdin = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"), true);
                        stdin.println("chmod + x " + file.getPath());
                        stdin.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件写入成功！");
    }

    public String getDatafromFile(String fileName) {
        BufferedReader reader = null;
        StringBuilder laststr = new StringBuilder();
        File file = new File(System.getProperty("user.dir") + "/" + fileName + ".json");
        //如果文件不存在，则新建一个
        if (!file.exists()) {
            try {
                file.createNewFile();
                Runtime run = Runtime.getRuntime();
                File wd = new File("/bin");
                try {
                    Process process = run.exec("/bin/bash", null, wd);
                    if (null != process) {
                        PrintWriter stdin = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"), true);
                        stdin.println("chmod + x " + file.getPath());
                        stdin.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getPath());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr.toString();
    }

}
