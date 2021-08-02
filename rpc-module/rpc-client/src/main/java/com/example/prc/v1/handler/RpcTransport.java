package com.example.prc.v1.handler;

import com.exampl.rpc.v1.request.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p>
 *          rpc 通讯的载荷，真正的socket
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class RpcTransport {

    private final String host;

    private final int port;

    public RpcTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 获取一个新的 socket 连接
     * @return              socket
     * @throws IOException  socket IOException
     */
    private Socket newSocket() throws IOException {
        return new Socket(this.host, this.port);
    }


    /**
     * 发送远程请求
     * @param request   请求协议体
     * @return          执行结果
     */
    public Object send(RpcRequest request) {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {

            // 拿到socket
            Socket socket = this.newSocket();
            socket.isClosed();
            // 通过socket写服务端执行协议体
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);

            // 获取执行结果
            ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
