package com.example.test.service;

/**
 * <p>
 *      测试 userService 接口
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/10/19 20:53
 **/
public interface UserService {

    /**
     * 根据用户ID获取用户名称
     * @return
     */
    String getData() throws Exception;

}
