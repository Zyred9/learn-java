package com.example.learn.es;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QueryPage {

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 每页记录数
     */
    private Integer size;
}
