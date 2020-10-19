package com.example.ibatis.v2;

import com.example.ibatis.doman.Blog;
import com.example.ibatis.mapper.BlogMapper;
import com.example.ibatis.v2.session.SqlSession;
import com.example.ibatis.v2.session.SqlSessionFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 16:46
 **/
public class MybatisV2Test {

    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactory();
        SqlSession session = factory.build().openSession();

        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = mapper.selectById(1);
        Blog blog1 = mapper.selectById(1);

        System.out.println("第一次查询" + blog);
        System.out.println("第二次查询" + blog1);


        SqlSession session1 = factory.build().openSession();
        BlogMapper mapper1 = session1.getMapper(BlogMapper.class);
        Blog blog3 = mapper1.selectById(1);
        Blog blog4 = mapper1.selectById(1);

        System.out.println("第一次查询" + blog3);
        System.out.println("第二次查询" + blog4);
    }

}
