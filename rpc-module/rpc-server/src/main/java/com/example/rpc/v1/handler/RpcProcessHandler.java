package com.example.rpc.v1.handler;

import com.exampl.rpc.v1.request.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Objects;

/**
 * <p>
 *  服务端通过 ServerSocket 进行对客户端请求的处理
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class RpcProcessHandler implements Runnable {

    private final Socket socket;
    private final Object service;

    public RpcProcessHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            // 从socket 中获取输入流
            ois = new ObjectInputStream(socket.getInputStream());
            // 读取到结果后，开始执行目标方法
            RpcRequest request = (RpcRequest) ois.readObject();
            if (Objects.nonNull(request)) {
                // 调用服务端执行服务端内容
                Object rs = this.invokeTargetMethod(request);

                System.out.println("服务端反射执行结果: " + rs);

                // 执行完毕后，写出到客户端

                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(rs);
                oos.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private Object invokeTargetMethod(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName(request.getClassName());

        Method method = clazz.getMethod(request.getMethodName(), request.getTypes());

        return method.invoke(this.service, request.getArgs());
    }
}
