package com.example.test.service.impl;

import com.example.spring.framework.annotation.Autowired;
import com.example.spring.framework.annotation.Service;
import com.example.test.service.OrderService;
import com.example.test.service.UserService;

/**
 * <p>
 *
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/10/19 20:57
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Override
    public String getOrderByUserId(String userId) {
        return this.userService.getUserNameById(userId);
    }
}
