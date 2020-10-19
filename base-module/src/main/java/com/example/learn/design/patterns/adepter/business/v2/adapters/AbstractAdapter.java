package com.example.learn.design.patterns.adepter.business.v2.adapters;

import com.example.learn.design.patterns.adepter.business.PassportService;
import com.example.learn.design.patterns.adepter.business.ResultMsg;

/**
 * <p>
 *      2.创建顶层的抽象适配器，继承原有的功能，和实现 target, 并且创建方法loginForRegister
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 10:10
 **/
public abstract class AbstractAdapter extends PassportService implements ILoginAdapter {

    protected ResultMsg loginForRegister(String username, String password) {
        if (null == password) {
            password = "THIRD_EMPTY";
        }
        super.regist(username, password);
        return super.login(username, password);
    }

}
