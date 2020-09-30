package com.example.onebatis.executor.impl;

import com.example.onebatis.builder.SqlBuilder;
import com.example.onebatis.executor.Executor;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class BaseExecutor implements Executor {


    @Override
    public <T> List<T> query(Object[] parameters, SqlBuilder sqlBuilder) {


        return null;
    }

    @Override
    public int update(Object[] parameters, SqlBuilder sqlBuilder) {
        return 0;
    }
}
