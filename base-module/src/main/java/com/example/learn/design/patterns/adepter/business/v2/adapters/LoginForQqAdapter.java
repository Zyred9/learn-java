package com.example.learn.design.patterns.adepter.business.v2.adapters;

import com.example.learn.design.patterns.adepter.business.ResultMsg;

/**
 * <p>
 *      3.QQ登录的适配器，主要继承最顶层的抽象适配器
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 10:15
 **/
public class LoginForQqAdapter extends AbstractAdapter {

    /**
     * 校验传入的实现类是不是LoginForQqAdapter
     * @param adapter
     * @return
     */
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQqAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {

        if (!this.support(adapter)){
            return null;
        }

        return super.loginForRegister(id, null);
    }
}
