package com.example.mybatis.plugin.interceptor;

import com.example.mybatis.domain.Fee;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * <p>
 * 因为费用表数据量比较大，每月的数据是独立管理的，所以把年度的费用表拆分成了12张表：fee_202001- fee_202012。
 * 费用表三个字段：id，费用金额fee_amt，费用日期fee_date。
 * <p>
 * Mapper.xml中只配置一个SQL：
 * select id, fee_amt，fee_date from fee where fee_date = #{ feeDate }
 * <p>
 * 当按费用日期查询的时候，自动把逻辑表名改成对应的月份表。例如fee_date=20200510，
 * 此时需要把表名修改为select id, fee_amt，fee_date from fee_202005 where fee_date =…
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/16 10:21
 **/
@Intercepts({
        @Signature(
                // 拦截executor类, 拦截的方法 query
                type = Executor.class, method = "query",
                // Invocation 对象中 args参数类型
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
})
public class LevelOfTableInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(LevelOfTableInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        // 获取MapperStatement对象
        MappedStatement ms = (MappedStatement) args[0];
        // 获取到sql对象
        BoundSql boundSql = ms.getBoundSql(args[1]);
        // 拿到传入的参数
        if (!(args[1] instanceof Fee)) {
            return invocation.proceed();
        }
        Fee fee = (Fee)args[1];
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String afterFormat = " fee_" + format.format(fee.getFeeDate()) + " ";

        String sql = boundSql.getSql();

        // select id, fee_amt as feeAmt, fee_date as feeDate from fee where date_format(fee_date, '%Y-%m') = date_format(?, '%Y-%m')
        logger.info("更改前的sql : {}" + sql);

        sql = sql.replaceAll(" fee ", afterFormat);

        // select id, fee_amt as feeAmt, fee_date as feeDate from fee_202009 where date_format(fee_date, '%Y-%m') = date_format(?, '%Y-%m')
        logger.info("更改后的sql : {}" + sql);

        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), sql, boundSql.getParameterMappings());
        Field sqlSourceField = MappedStatement.class.getDeclaredField("sqlSource");
        sqlSourceField.setAccessible(true);
        sqlSourceField.set(ms, sqlSource);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
