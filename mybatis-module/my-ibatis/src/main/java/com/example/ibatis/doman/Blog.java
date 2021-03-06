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
}
