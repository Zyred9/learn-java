package com.example.ibatis.v2.plugin;

import com.example.ibatis.v2.executor.Executor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 13:59
 **/
public class InterceptorChain {

    private final List<Interceptor> interceptors = new ArrayList<>();


    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }



    public boolean hasPlugin() {
        if (interceptors.size() > 0){
            return false;
        }
        return true;
    }
}
