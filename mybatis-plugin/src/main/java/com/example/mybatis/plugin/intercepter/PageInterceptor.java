package com.example.mybatis.plugin.intercepter;

import com.example.mybatis.plugin.util.Page;
import com.example.mybatis.plugin.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * <p>
 * 自定义分页插件拦截器
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/15 14:05
 **/
@Slf4j
@Intercepts(
        {
                @Signature(
                        // 拦截executor类, 拦截的方法 query
                        type = Executor.class, method = "query",
                        // Invocation 对象中 args参数类型
                        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
                )
        }
)
public class PageInterceptor implements Interceptor {

    /**
     * 真正拦截执行的方法
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("page plugin running ...");
        Object[] args = invocation.getArgs();

        MappedStatement ms = (MappedStatement) args[0];

        BoundSql boundSql = ms.getBoundSql(args[1]);

        Page page = PageUtil.getPage();

        // 无需分页
        if (page == null) {
            return invocation.proceed();
        }

        // 需要分页，则修改sql逻辑
        String sql = boundSql.getSql();
        log.info("before sql ： {}", sql);
        String limit = String.format(" limit %s, %s", page.getOffset(), page.getLimit());
        sql += limit;
        log.info("after sql ： {}", sql);

        // 利用反射，重新把SqlSource对象写入到MappedStatment对象中
        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), sql, boundSql.getParameterMappings());
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(ms, sqlSource);

        // 执行被拦截的方法
        Object result = invocation.proceed();
        PageUtil.remove();
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
