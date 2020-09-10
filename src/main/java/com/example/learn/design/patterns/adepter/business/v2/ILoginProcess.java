package com.example.learn.design.patterns.adepter.business.v2;

import com.example.learn.design.patterns.adepter.business.ResultMsg;
import com.example.learn.design.patterns.adepter.business.v2.adapters.AbstractAdapter;

/**
 * <p>
 *      7.创建接口，登录做处理
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 10:31
 **/
public interface ILoginProcess {

    /**
     * 登录处理逻辑接口
     * @param id            登录条件
     * @param clazz         继承适配器类的所有三方的登录逻辑
     * @return
     */
    ResultMsg loginProcess(String id, Class<? extends AbstractAdapter> clazz);


}
