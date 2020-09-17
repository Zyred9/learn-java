package com.example.ibatis.v2.session;

import com.example.ibatis.v2.binding.MapperRegistry;
import com.example.ibatis.v2.executor.CachingExecutor;
import com.example.ibatis.v2.executor.Executor;
import com.example.ibatis.v2.executor.SimpleExecutor;
import com.example.ibatis.v2.plugin.InterceptorChain;
import lombok.SneakyThrows;

import java.util.*;

/**
 * <p>
 *      mybatis的全局配置文件
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 13:50
 **/
public class Configuration {

    private static final String regex = "--";
    public static final String DOT = ".";

    /**
     * 类初始化的时候，完成配置文件的加载
     */
    public final static ResourceBundle properties;
    public final static ResourceBundle sqlMappings;
    public static boolean cacheEnable_ = false;
    static {
        // 加载数据库连接信息及全局配置
        properties = ResourceBundle.getBundle("mybatis");
        // 加载sql与接口关系文件
        sqlMappings = ResourceBundle.getBundle("sql_plus");
    }

    /** 接口方法与SQL关系 **/
    public final static Map<String, String> mappedStatements = new HashMap<>();

    /** 所有Mapper接口 **/
    private List<Class<?>> mapperList = new ArrayList<>();

    /** 类所有文件 **/
    private List<String> classPaths = new ArrayList<>();

    /** 接口与工厂类关系 **/
    public static final MapperRegistry MAPPER_REGISTRY = new MapperRegistry();

    /** 插件 **/
    private InterceptorChain interceptorChain = new InterceptorChain();


    @SneakyThrows
    public Configuration() {
        for (String mapping : sqlMappings.keySet()) {
            // 拿到被执行的mapper方法
            String statementId = sqlMappings.getString(mapping).split(regex)[0];
            // mapper方法的返回值
            String pojoName = sqlMappings.getString(mapping).split(regex)[1];

            // 拿到Mapper接口的全路径名称
            String className = statementId.substring(0, statementId.lastIndexOf(DOT));

            // 实例化mapper接口 和 mapper方法中返回的结果对象
            Class<?> mapper = Class.forName(className);
            Class<?> pojo = Class.forName(pojoName);

            MAPPER_REGISTRY.addMapper(mapper, pojo);
            mappedStatements.put(mapping, statementId);
        }
        String cacheEnable = properties.getString("cache.enabled");
        cacheEnable_ = Objects.equals(cacheEnable, "true") ? true : false;

    }

    public Executor newExecutor() {
        Executor executor = null;

        // 创建缓存执行器
        if (Boolean.TRUE == (cacheEnable_)){
            executor = new CachingExecutor(new SimpleExecutor());
        }
        // 使用普通执行器
        else {
            executor = new SimpleExecutor();
        }

        // 拦截所有 query方法
        if (interceptorChain.hasPlugin()){
            return (Executor) this.interceptorChain.pluginAll(executor);
        }
        return executor;
    }

    public boolean hasStatement(String statementId) {
        return mappedStatements.containsKey(statementId);
    }

    public String getMappedStatement(String statementId){
        if (mappedStatements.containsKey(statementId)){
            return mappedStatements.get(statementId);
        }
        throw new NullPointerException("找不到sql");
    }
}
