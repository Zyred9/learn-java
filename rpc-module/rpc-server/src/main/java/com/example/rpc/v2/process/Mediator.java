package com.example.rpc.v2.process;

import com.exampl.rpc.v1.request.RpcRequest;
import com.example.rpc.v2.bean.BeanMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 中间介质，存储存在 @RemoteService 标记的类对应的方法映射
 * <p>
 * 使用单例模式，对该类初始化，目的是为了防止多次初始化后，对容器中保存的值不一致的情况
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class Mediator {

    public static final Map<String, BeanMethod> mediator = new ConcurrentHashMap<>();

    private static volatile Mediator INSTANCE;

    private Mediator() {
//        throw new RuntimeException("Mediator can not be constructor init.");
    }


    public static Mediator getInstance() {
        if (INSTANCE == null) {
            synchronized (Mediator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Mediator();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 通过反射执行目标方法后返回结果，主要是从远程的协议中获取执行目标的方法以及参数
     *
     * @param request   远程执行请求协议体
     * @return          方法调用后的结果
     */
    public Object processor(RpcRequest request) {

        String key = request.getClassName().concat(".").concat(request.getMethodName());
        BeanMethod beanMethod = mediator.get(key);

        if (beanMethod == null) { return null; }

        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
            return method.invoke(bean, request.getArgs());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


}
