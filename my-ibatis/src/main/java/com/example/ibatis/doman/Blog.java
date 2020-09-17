package com.example.ibatis.doman;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @Author: qingshan
 */
@Setter
@Getter
@ToString
public class Blog implements Serializable{

    /** 文章ID **/
    private Integer bid;
    
    /** 文章标题 **/
    private String name;
    
    /** 文章作者ID **/
    private Integer authorId;

    public static void main(String[] args) throws Exception {
        Blog blog = Blog.class.newInstance();

        for (Field field : Blog.class.getDeclaredFields()) {
            if (field.getType().equals(String.class)){
                field.set(blog, "张三");
            }
            if (field.getType().equals(Integer.class)){
                field.set(blog, 1);
            }
        }

        System.out.println(blog);
    }
}
