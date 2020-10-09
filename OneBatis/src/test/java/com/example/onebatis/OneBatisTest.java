package com.example.onebatis;

import com.example.onebatis.builder.Resources;
import com.example.onebatis.session.SqlSession;
import com.example.onebatis.session.SqlSessionFactory;
import com.example.onebatis.session.SqlSessionFactoryBuilder;
import com.example.test.entity.User;
import com.example.test.mapper.UserMapper;
import org.junit.Before;
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

    private SqlSessionFactory build;

    private User user;

    @Before
    public void before(){
        user = new User();
        user.setUserName("苟七");
        user.setPassword("12345");
        user.setAddress("深圳");
        user.setPhone("10086");

        InputStream stream = Resources.getResourceAsStream("classpath:mybatis-config.xml");
        build = new SqlSessionFactoryBuilder().build(stream);
    }


    @Test
    public void selectList(){
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> page = mapper.getUserPage("10086");
        page.stream().forEach(System.out::println);
    }

    @Test
    public void insert(){
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.inertUser(user);
        System.out.println(i);
    }
}
