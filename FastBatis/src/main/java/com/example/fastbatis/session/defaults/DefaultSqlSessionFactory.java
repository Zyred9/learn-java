package com.example.fastbatis.session.defaults;

import com.example.fastbatis.session.Configuration;
import com.example.fastbatis.session.SqlSession;
import com.example.fastbatis.session.SqlSessionFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 14:03
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return null;
    }
}
