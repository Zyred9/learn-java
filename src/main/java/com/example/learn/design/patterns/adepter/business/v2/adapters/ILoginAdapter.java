package com.example.learn.design.patterns.adepter.business.v2.adapters;

import com.example.learn.design.patterns.adepter.business.ResultMsg;

/**
 * <p>
 *      1.创建登录适配器，，适配器模式种 target角色
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 10:07
 **/
public interface ILoginAdapter {

    /**
     * 校验适配器是否属于等级适配器的子类
     * @param adapter
     * @return
     */
    boolean support(Object adapter);

    /**
     * 登录接口
     * @param id
     * @param adapter       传入不同的适配器，来判断走对应的逻辑
     * @return
     */
    ResultMsg login(String id, Object adapter);
}
