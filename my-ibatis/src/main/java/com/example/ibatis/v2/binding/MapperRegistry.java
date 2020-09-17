package com.example.ibatis.v2.binding;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 13:58
 **/
public class MapperRegistry {

    private static Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap<>();

    public void addMapper(Class<?> mapper, Class<?> pojo) {

    }
}
