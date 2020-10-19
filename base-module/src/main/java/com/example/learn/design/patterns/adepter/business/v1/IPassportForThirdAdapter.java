package com.example.learn.design.patterns.adepter.business.v1;

import com.example.learn.design.patterns.adepter.business.PassportService;
import com.example.learn.design.patterns.adepter.business.ResultMsg;

/**
 * <p>
 *      2.创建代理三方等级的适配器类，继承原有系统的等级逻辑，然后实现新三方登录的接口
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 9:57
 **/
public class IPassportForThirdAdapter extends PassportService implements IPassportForThird {

    @Override
    public ResultMsg loginForQQ(String openId) {
        return loginForRegister(openId, null);
    }

    @Override
    public ResultMsg loginForWechat(String openId) {
        return loginForRegister(openId, null);
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return loginForRegister(token, null);
    }

    @Override
    public ResultMsg loginForTelPhone(String phone, String code) {
        return loginForRegister(phone, null);
    }


    private ResultMsg loginForRegister(String username,String password){
        if(null == password){
            // 如果密码传入时候是空的，那么给一个系统默认的登录密码
            password = "THIRD_EMPTY";
        }
        super.regist(username,password);
        return super.login(username,password);
    }
}
