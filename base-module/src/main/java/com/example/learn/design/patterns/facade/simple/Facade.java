package com.example.learn.design.patterns.facade.simple;

/**
 * <p>
 *      门面模式
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 9:43
 **/
public class Facade {

    private IntegralService integralService;
    private LogisticsService logisticsService;
    private PayService payService;

    public void facade (Goods goods){

        integralService = new IntegralService();
        payService = new PayService();
        logisticsService = new LogisticsService();

        if (integralService.decrementIntegral(goods)) {
            if (payService.pay(goods)) {
                String logistics = logisticsService.logistics(goods);
                System.out.println("发货单号：" + logistics);
            }
        }

    }


}
