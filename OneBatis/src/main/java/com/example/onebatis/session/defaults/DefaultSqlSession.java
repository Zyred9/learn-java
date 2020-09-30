package com.example.onebatis.session.defaults;

import com.example.onebatis.binding.MapperRegistry;
import com.example.onebatis.builder.SqlBuilder;
import com.example.onebatis.executor.Executor;
import com.example.onebatis.session.Configuration;
import com.example.onebatis.session.SqlSession;
import com.example.test.mapper.UserMapper;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 14:00
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;
    private boolean autoCommit;

    public DefaultSqlSession(Configuration configuration, Executor ex, boolean autoCommit) {
        this.configuration = configuration;
        this.executor = ex;
        this.autoCommit = autoCommit;
    }

    @Override
    public <T> T getMapper(Class<?> clazz) {
        MapperRegistry registry = this.configuration.getMapperRegistry();
        if (!registry.hasMapper(clazz)) {
            throw new RuntimeException(clazz.getSimpleName() + " not found.");
        }
        return (T) registry.getMapper(clazz, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public Object selectList(SqlBuilder sqlBuilder, Object[] args, Class object) {
        return this.executor.query(args, sqlBuilder);
    }

    @Override
    public Object flushStatements() {
        return null;
    }
}
