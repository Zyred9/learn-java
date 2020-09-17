package com.example.ibatis.v2.executor;

import com.example.ibatis.v2.cache.CacheKey;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 15:23
 **/
public class CachingExecutor implements Executor {

    private Executor delegate;

    /**
     * 查询缓存
     **/
    private static Map<Integer, Object> cache = new HashMap<>();

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T query(String statementId, Object[] parameters, Class<?> resultPojo) {
        Integer cacheKey = createCacheKey(statementId, parameters);
        // 命中缓存
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }
        // 走数据库查询
        Object result = delegate.query(statementId, parameters, resultPojo);
        cache.put(cacheKey, result);
        return (T) result;
    }

    @Override
    public int update(String statementId, Object[] parameter) {
//        throw new IllegalAccessException("");
        return 0;
    }


    private Integer createCacheKey(String statementId , Object[] param) {
        CacheKey cacheKey = new CacheKey();
        cacheKey.updateAll(param);
        cacheKey.update(statementId);
        return cacheKey.hashCode();
    }

}
