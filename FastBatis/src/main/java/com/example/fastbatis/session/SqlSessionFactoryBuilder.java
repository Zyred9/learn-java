package com.example.fastbatis.session;

import com.example.fastbatis.builder.XmlConfigBuilder;
import com.example.fastbatis.session.defaults.DefaultSqlSessionFactory;

import java.io.InputStream;

/**
 * <p>
 *      用于创建SqlSessionFactory
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 14:05
 **/
public class    SqlSessionFactoryBuilder {

    /**
     * 这里用于解析，初始化整个FastBatis上下文
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream){
        XmlConfigBuilder builder = new XmlConfigBuilder(inputStream);
        return build(builder.parse());
    }


    private SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
