package com.example.learn.design.patterns.singleton.simple.create.register.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 容器式单例
 *  通过枚举升级而来，
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 15:01
 * @company 中再云图技术有限公司
 **/
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String, Object> container = new ConcurrentHashMap<>();

    public static Object getInstance(Class clazz) {
        String className = clazz.getName();
        if (!container.containsKey(className)) {
            try {
                Object instance = clazz.newInstance();
                container.put(className, instance);
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        } else {
            return container.get(className);
        }
        return null;
    }

}
