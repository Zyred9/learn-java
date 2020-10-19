package com.example.test.service;

/**
 * <p>
 *      订单服务
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/10/19 20:55
 **/
public interface OrderService {

    /**
     * 根据用户ID获取订单
     * @param userId
     * @return
     */
    String getOrderByUserId (String userId);

}
