package com.xn.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileOperate {
    public static void down(HttpServletRequest request, HttpServletResponse response,String path){
        try {
            File file = new File(path);
            String fileName = file.getName();
            String ext = fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            //清空response
            response.reset();
            //设置response的Header
            String filenameString = new String(fileName.getBytes("gbk"),"iso-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + filenameString);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
