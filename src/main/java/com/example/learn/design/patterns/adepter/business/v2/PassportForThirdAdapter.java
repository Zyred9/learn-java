package com.example.learn.design.patterns.adepter.business.v2;

import com.example.learn.design.patterns.adepter.business.ResultMsg;
import com.example.learn.design.patterns.adepter.business.v2.adapters.AbstractAdapter;
import com.example.learn.design.patterns.adepter.business.v2.adapters.LoginForPhoneAdapter;
import com.example.learn.design.patterns.adepter.business.v2.adapters.LoginForTokenAdapter;
import com.example.learn.design.patterns.adepter.business.v2.adapters.LoginForWechatAdapter;

/**
 * <p>
 *      6.该适配器主要是为了完成对接三方登录的，所以不需要放入到 adapters 文件夹中
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 10:23
 **/
public class PassportForThirdAdapter implements IPassportForThird, ILoginProcess {
    @Override
    public ResultMsg loginForQQ(String openId) {
        return this.loginProcess(openId, LoginForPhoneAdapter.class);
    }

    @Override
    public ResultMsg loginForWechat(String openId) {
        return this.loginProcess(openId, LoginForWechatAdapter.class);
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return this.loginProcess(token, LoginForTokenAdapter.class);
    }

    @Override
    public ResultMsg loginForTelPhone(String phone, String code) {
        return this.loginProcess(phone, LoginForPhoneAdapter.class);
    }


    @Override
    public ResultMsg loginProcess(String id, Class<? extends AbstractAdapter> clazz) {

        try {
            AbstractAdapter adapter = clazz.newInstance();
            return adapter.login(id, null);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
