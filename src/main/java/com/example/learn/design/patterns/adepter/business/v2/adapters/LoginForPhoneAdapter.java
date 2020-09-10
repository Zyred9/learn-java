package com.example.learn.design.patterns.adepter.business.v2.adapters;

import com.example.learn.design.patterns.adepter.business.ResultMsg;

/**
 * <p>
 *      4.创建电话登录的适配器，实现最顶层抽象适配器
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 10:19
 **/
public class LoginForPhoneAdapter extends AbstractAdapter{
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForPhoneAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        if (!this.support(adapter)){
            return null;
        }
        return super.loginForRegister(id, null);
    }
}
