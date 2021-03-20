package com.example.mybatis;

import com.example.mybatis.domain.Fee;
import com.example.mybatis.domain.User;
import com.example.mybatis.mapper.FeeMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.plugin.util.PageUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * mybatis 原生使用 Junit 单元测试
 *
 * @author zyred
 * @since v 0.1
 */
public class MyBatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void customPagePlugin() {
//        PageUtil.setPage(0, 4);
        SqlSession session = this.sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> page = mapper.getUserPage("10086");
//        System.out.println(page.size());
        page.forEach(System.out::println);
    }

    @Test
    public void selectFee() throws ParseException {
        PageUtil.setPage(0, 4);
        SqlSession session = this.sqlSessionFactory.openSession();
        FeeMapper mapper = session.getMapper(FeeMapper.class);
        Fee fee = new Fee();
        fee.setFeeDate(new Date());
        List<Fee> fees = mapper.selectFee(fee);
        System.out.println(fees.size());
        fees.forEach(System.out::println);
    }
}
