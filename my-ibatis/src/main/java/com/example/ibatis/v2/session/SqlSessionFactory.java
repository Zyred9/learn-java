package com.example.ibatis.v2.session;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 13:50
 **/
public class SqlSessionFactory {

    private Configuration configuration;

    /**
     * 初始化 configuration对象
     * @return
     */
    public SqlSessionFactory build(){
        configuration = new Configuration();
        return this;
    }




}
