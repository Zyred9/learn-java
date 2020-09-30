package com.example.onebatis;

import com.example.onebatis.builder.Resources;
import com.example.onebatis.session.SqlSession;
import com.example.onebatis.session.SqlSessionFactory;
import com.example.onebatis.session.SqlSessionFactoryBuilder;
import com.example.test.entity.User;
import com.example.test.mapper.UserMapper;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 17:26
 **/
public class OneBatisTest {

    @Test
    public void run(){
        InputStream stream = Resources.getResourceAsStream("classpath:mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> page = mapper.getUserPage("10086");
        page.stream().forEach(System.out::println);
    }
}
