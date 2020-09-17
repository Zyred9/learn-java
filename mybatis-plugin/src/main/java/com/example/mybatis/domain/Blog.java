package com.example.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
