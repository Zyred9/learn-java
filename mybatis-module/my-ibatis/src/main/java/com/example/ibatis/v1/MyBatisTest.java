package com.example.ibatis.v1;


import com.example.ibatis.mapper.BlogMapper;

import java.io.*;

/**
 * @Author: qingshan
 * MyBatis Maven演示工程
 */
public class MyBatisTest {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = new SqlSession(new Configuration(), new Executor());
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("blogMapper.class"));
        oos.writeObject(mapper);

        mapper.selectById(1);
    }

}
