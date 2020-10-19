package com.example.mybatis.plugin.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *      sql 语句
 *
 *      原sql ： select * from table where id = ?
 *
 *      prefix = select count(1) from (
 *      suffix = ) as total
 *
 *      组合后： select count(1) from ( select * from table where id = ? ) as total
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 9:15
 **/
@Setter
@Getter
@ToString
@AllArgsConstructor
public class SqlLang {

    /** 增强sql的前缀 **/
    private String prefix;

    /** 增强sql的后缀 **/
    private String suffix;

}
