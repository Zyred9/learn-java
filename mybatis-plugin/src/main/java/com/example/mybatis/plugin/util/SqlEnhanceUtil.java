package com.example.mybatis.plugin.util;

import java.util.Objects;

/**
 * <p>
 *      sql 增强工具
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 9:14
 **/
public class SqlEnhanceUtil {

    /** 这里使用 ThreadLocal 的目的是因为
     * 每一条sql对应的是一个 session 对象，一个session 对象占用 一个线程
     * 为了避免本session拿到其他session 的sql，所以需要使用线程独享的变量
     * **/
    private static final ThreadLocal<SqlLang> local_lang = new ThreadLocal<>();
    private static final String space = " ";

    public static void setSql(String prefix, String suffix){

        if (Objects.isNull(prefix) || Objects.isNull(suffix)){
            throw new NullPointerException("Enhance sql can not be null.");
        }

        // 添加空格，避免sql出错
        prefix += space;
        suffix = space + suffix;

        local_lang.set(new SqlLang(prefix, suffix));
    }

    public static void remove(){
        local_lang.remove();
    }

    public static SqlLang getSql(){
        return local_lang.get();
    }

}
