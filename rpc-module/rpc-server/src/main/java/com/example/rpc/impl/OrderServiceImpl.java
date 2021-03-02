package com.example.rpc.impl;

import com.exampl.rpc.v1.service.IOrderService;
import com.example.rpc.v2.annotion.RemoteService;

/**
 * <p>
 *      真正的订单接口实现类
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@RemoteService
public class OrderServiceImpl implements IOrderService {


    @Override
    public String allOrders() {
        return "RETURN ALL ORDER";
    }


}
