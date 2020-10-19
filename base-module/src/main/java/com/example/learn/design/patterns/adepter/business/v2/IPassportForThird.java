package com.example.learn.design.patterns.adepter.business.v2;

import com.example.learn.design.patterns.adepter.business.ResultMsg;

/**
 * <p>
 *      1.系统扩展三方登录的目标接口，适配器模式种 target角色
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 9:52
 **/
public interface IPassportForThird {

    /**
     * 通过qq的openId等级
     * @param openId
     * @return
     */
    ResultMsg loginForQQ(String openId);

    /**
     * 通过Wechat的openId等级
     * @param openId
     * @return
     */
    ResultMsg loginForWechat(String openId);

    /**
     * 通过系统原有的token等级
     * @param token
     * @return
     */
    ResultMsg loginForToken(String token);

    /**
     * 通过手机号等级
     * @param phone
     * @param code
     * @return
     */
    ResultMsg loginForTelPhone(String phone,String code);

}
