package com.example.ibatis.v1;


import com.example.ibatis.mapper.BlogMapper;

/**
 * @Author: qingshan
 * MyBatis Maven演示工程
 */
public class MyBatisTest {

    public static void main(String[] args) {
        SqlSession sqlSession = new SqlSession(new Configuration(), new Executor());
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);


        mapper.selectById(1);
    }

}
