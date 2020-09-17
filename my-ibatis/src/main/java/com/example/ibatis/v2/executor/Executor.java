package com.example.ibatis.v2.executor;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 14:41
 **/
public interface Executor {


    /**
     *  查询，该方法会被拦截
     * @param sql               sql语句
     * @param parameters        执行接口方法传入的参数
     * @param resultPojo        返回值类型
     * @param <T>               执行逻辑后返回的内容
     * @return
     */
    <T> T query(String sql, Object[] parameters, Class<?> resultPojo);


    /**
     * 更新数据库内容
     * @param statementId       mapper类名称 + 执行方法的名称
     * @param parameter         参数
     * @return
     */
    int update(String statementId, Object[] parameter);

}
