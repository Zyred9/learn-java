package com.example.socket.base;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 *      web socket 服务端， 先启动服务端，然后启动客户端发送数据
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/8 9:53
 **/
public class SocketServer {


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8848);
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            byte[] buff = new byte[1024];
            while ((inputStream.read(buff)) != -1){
                System.out.println(new String(buff));
            }
            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("我是二哥二".getBytes());
            outputStream.flush();

            outputStream.close();
            inputStream.close();

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
