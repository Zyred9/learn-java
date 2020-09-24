package com.example.fastbatis.binding;

import com.example.fastbatis.session.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/24 16:54
 **/
public class MapperRegistry {

    private Configuration config;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    public boolean hasMapper(Class<?> type) {
        return knownMappers.containsKey(type);
    }

    /**
     * 注册mapper 到 mapper容器中
     *
     * @param type
     * @param <T>
     */
    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                knownMappers.put(type, new MapperProxyFactory<>(type));
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }
}
