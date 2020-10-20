package com.example.test.service.impl;

import com.example.spring.framework.annotation.Service;
import com.example.test.service.UserService;

import java.util.Random;

/**
 * <p>
 *
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/10/19 20:55
 **/
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getData() {
        return new Random().nextInt() + "";
    }
}
