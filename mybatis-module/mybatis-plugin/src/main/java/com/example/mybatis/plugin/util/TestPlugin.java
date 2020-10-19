package com.example.mybatis.plugin.util;

import com.example.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 9:46
 **/
public class TestPlugin {

    @Test
    public void run() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 没加下面这句话的样子：SELECT id, user_name, password, address, phone FROM t_user where phone = ?
        // 加上了这句话的样子 ： SELECT COUNT(1) FROM ( SELECT id, user_name, password, address, phone FROM t_user where phone = ? ) AS TOTAL
        SqlEnhanceUtil.setSql("SELECT COUNT(1) FROM (", ") AS TOTAL");
        mapper.getUserPage("123456");
    }

}
