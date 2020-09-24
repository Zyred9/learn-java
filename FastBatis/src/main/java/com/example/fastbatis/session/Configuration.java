package com.example.fastbatis.session;

import com.example.fastbatis.DataSource;
import com.example.fastbatis.binding.MapperProxyFactory;
import com.example.fastbatis.binding.MapperRegistry;
import com.example.fastbatis.builder.SqlBuilder;
import com.example.fastbatis.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * <p>
 * 总配置文件
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 13:54
 **/
public class Configuration {

    private String cacheEnabled;
    private String logImpl;
    private String lazyLoadingEnabled;
    private List<Interceptor> interceptors = new ArrayList<>();
    private DataSource dataSource;
    private ResourceBundle properties;

    /** statementId -> sql **/
    private HashMap<String, SqlBuilder> sqlMapping = new HashMap<>();
    private MapperRegistry mapperRegistry = new MapperRegistry(this);

    public void addInterceptor(Interceptor i) {
        interceptors.add(i);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setProperties(ResourceBundle properties) {
        this.properties = properties;
    }

    public ResourceBundle getProperties() {
        return this.properties;
    }

    public void addSqlMapping(String statementId, SqlBuilder sql) {
        this.sqlMapping.put(statementId, sql);
    }

    public void addMapper(Class<?> clazz) {
        this.mapperRegistry.addMapper(clazz);
    }

    public boolean hasStatement(String statementId){
        return this.sqlMapping.containsKey(statementId);
    }

    public SqlBuilder getMappedStatement(String statementId) {
        return sqlMapping.get(statementId);
    }
}
