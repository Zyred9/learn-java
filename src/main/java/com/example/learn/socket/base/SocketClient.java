package com.example.learn.socket.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * <p>
 * web socket 客户端
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/8 10:00
 **/
public class SocketClient {

    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 8848);
            Socket socket = new Socket();
            socket.connect(address);

            // 输出
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("我是大哥大".getBytes());
            outputStream.flush();

            // 输入
            InputStream inputStream = socket.getInputStream();
            byte[] buff = new byte[1024];
            while (inputStream.read(buff) != -1) {
                System.out.println(new String(buff));
            }


            outputStream.close();
            inputStream.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
