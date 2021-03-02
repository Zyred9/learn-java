package com.exampl.rpc.v1.service;

/**
 *  统一管理接口，所有项目依赖此接口
 *
 * @author zyred
 * @since v 0.1
 **/
public interface IUserService {


    String getUserById(Integer id);

    String getUserList();
}
