package com.example.ibatis.v2.executor;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 15:22
 **/
public class SimpleExecutor implements Executor{



    @Override
    public <T> T query(String sql, Object[] parameters, Class<?> resultPojo) {
        


        return null;
    }

    @Override
    public int update(String statementId, Object[] parameter) {
        return 0;
    }

}
