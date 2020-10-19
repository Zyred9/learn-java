package com.example.learn.design.patterns.facade.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *  商品实体类
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 9:35
 * @company 中再云图技术有限公司
 **/
@Setter
@Getter
@AllArgsConstructor
public class Goods {

    private String goodsName;

    private int integral;

}
