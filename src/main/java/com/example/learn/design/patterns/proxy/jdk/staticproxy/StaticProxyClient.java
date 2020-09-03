package com.example.learn.design.patterns.proxy.jdk.staticproxy;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/1 8:57
 **/
public class StaticProxyClient {

    public static void main(String[] args) {
        Zhangsan zhangsan = new Zhangsan();
        Zhanglaosan zhanglaosan = new Zhanglaosan(zhangsan);

        zhanglaosan.findLove();
    }

}
