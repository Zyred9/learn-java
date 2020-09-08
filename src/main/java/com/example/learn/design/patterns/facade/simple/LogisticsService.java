package com.example.learn.design.patterns.facade.simple;

/**
 * <p>
 *      物流服务
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 9:34
 * @company 中再云图技术有限公司
 **/
public class LogisticsService {

    public String logistics(Goods goods){
        System.out.println(goods.getGoodsName() + "已发货");
        return "123456";
    }

}
