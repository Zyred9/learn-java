package com.example.fastbatis;

import cn.hutool.core.io.resource.ClassPathResource;
import com.example.fastbatis.builder.Resources;
import com.example.fastbatis.session.SqlSession;
import com.example.fastbatis.session.SqlSessionFactory;
import com.example.fastbatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 17:26
 **/
public class FastBatisTest {

    @Test
    public void run(){
        InputStream stream = Resources.getResourceAsStream("classpath:mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(stream);
        SqlSession sqlSession = build.openSession();

    }
}
