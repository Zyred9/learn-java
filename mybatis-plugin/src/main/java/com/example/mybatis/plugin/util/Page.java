package com.example.mybatis.plugin.util;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/15 15:56
 * @company 中再云图技术有限公司
 **/
@Setter
@Getter
public class Page {

    private int offset = 0;

    private int limit = 10;
    
}
