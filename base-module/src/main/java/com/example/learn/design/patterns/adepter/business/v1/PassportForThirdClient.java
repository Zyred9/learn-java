package com.example.learn.design.patterns.adepter.business.v1;

/**
 * <p>
 *      3.三方登录测试客户端
 *      这种方式不是很优雅，不同的登录接口应该有不同的登录适配器来对应。所以请看V2版本
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 10:01
 **/
public class PassportForThirdClient {

    public static void main(String[] args) {
        IPassportForThird passportForThirdAdapter = new IPassportForThirdAdapter();
        passportForThirdAdapter.loginForQQ("LKHGIEMKH214JLKIH2345432523213KL");
        passportForThirdAdapter.loginForTelPhone("18325011211", null);
        passportForThirdAdapter.loginForToken("LKHGIEMKH214JLKIH2345432523213KL");
        passportForThirdAdapter.loginForWechat("LKHGIEMKH214JLKIH2345432523213KL");

    }

}
