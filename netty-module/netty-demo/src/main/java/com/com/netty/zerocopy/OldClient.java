package com.com.netty.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class OldClient {
    public static void main(String args[]) throws Exception{
        Socket socket = new Socket("localhost", 8889);

        String fileName = "E:/BaiduYunDownload/test1.75g.zip";//这里找一个大文件，小的效果不明显
        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime =  System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) >= 0){
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送总字节数" + total + "，耗时" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
    }
}
