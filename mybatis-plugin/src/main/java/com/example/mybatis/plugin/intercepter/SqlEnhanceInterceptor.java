package com.example.mybatis.plugin.intercepter;

import com.example.mybatis.plugin.util.SqlEnhanceUtil;
import com.example.mybatis.plugin.util.SqlLang;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;


/**
 * <p>
 *      sql 增强拦截器，具体的增强逻辑参考 {@link SqlLang} 的注释
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 9:05
 **/
@Slf4j
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {
                        MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class
                }
        )
})
public class SqlEnhanceInterceptor implements Interceptor {

    private static final String CHR = "";
    private static final String DECLARED_FIELD_NAME = "sqlSource";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 一旦进入拦截器，先判断是否存在定义的 sql 增强
        SqlLang sqlLang = SqlEnhanceUtil.getSql();
        if (!validate(sqlLang)) {
            // 不存在 sql 增强的情况，则直接通过
            return invocation.proceed();
        }

        // 先拿到 invocation 中 args数组的内容
        Object[] args = invocation.getArgs();
        // 拿到 sql 的性质
        MappedStatement ms = (MappedStatement) args[0];
        // 取sql对象，方便后续取 sql 做修改
        BoundSql boundSql = ms.getBoundSql(args[1]);
        String sql = boundSql.getSql();

        String afterSql = new StringBuilder()
                .append(sqlLang.getPrefix())
                .append(sql)
                .append(sqlLang.getSuffix())
                .toString();

        SqlEnhanceUtil.remove();

        // 利用反射，重新设置 MappedStatement 对象中 sqlSource
        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(),
                afterSql, boundSql.getParameterMappings());
        Field field = MappedStatement.class.getDeclaredField(DECLARED_FIELD_NAME);
        field.setAccessible(true);
        field.set(ms, sqlSource);
        field.setAccessible(false);

        log.debug("<==");
        log.debug("After enhance sql : {}", afterSql);
        log.debug("==>");

        // 执行被拦截的方法
        return invocation.proceed();
    }


    /**
     * 校验
     *
     * @param sqlLang
     * @return
     */
    private static boolean validate(SqlLang sqlLang) {
        if (sqlLang == null) {
            return false;
        }
        if (sqlLang.getPrefix() == null || sqlLang.getPrefix() == CHR) {
            return false;
        }
        if (sqlLang.getSuffix() == null || sqlLang.getSuffix() == CHR) {
            return false;
        }
        return true;
    }


}
