package com.example.rpc.v2.handler;

import com.exampl.rpc.v1.request.RpcRequest;
import com.example.rpc.v2.process.Mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p>
 * 获取到socket 传输的内容，然后解析请求体，执行请求体中目标方法，拿到返回结果
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ProcessorHandler implements Runnable {

    private final Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(this.socket.getInputStream());
            RpcRequest request = (RpcRequest) ois.readObject();

            // 通过中介器执行结果
            Mediator mediator = Mediator.getInstance();
            Object rs = mediator.processor(request);

            System.out.println("服务端接收到并处理返回结果：" + rs);

            oos = new ObjectOutputStream(this.socket.getOutputStream());
            oos.writeObject(rs);
            oos.flush();
        } catch (Exception ex) {
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

    }
}
