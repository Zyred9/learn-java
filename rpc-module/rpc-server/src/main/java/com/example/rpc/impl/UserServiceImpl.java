package com.example.rpc.impl;

import com.exampl.rpc.v1.service.IUserService;
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
public class UserServiceImpl implements IUserService {


    @Override
    public String getUserById(Integer id) {
        return "RETURN USER BY ID";
    }

    @Override
    public String getUserList() {
        return "RETURN USER LIST";
    }
}
