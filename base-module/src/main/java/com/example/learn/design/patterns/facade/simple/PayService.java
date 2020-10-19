package com.example.learn.design.patterns.facade.simple;

/**
 * <p>
 *      支付服务
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 9:27
 **/
public class PayService {

    public boolean pay(Goods goods){
        System.out.println(goods.getGoodsName() + "支付成功");
        return true;
    }


}
