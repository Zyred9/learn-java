package com.example.learn.design.patterns.facade.simple;

/**
 * <p>
 *      积分服务
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 9:34
 * @company 中再云图技术有限公司
 **/
public class IntegralService {

    public boolean decrementIntegral(Goods goods){
        System.out.println(goods.getGoodsName() + "积分扣减成功 ：" + goods.getIntegral());
        return true;
    }

}
