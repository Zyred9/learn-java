package com.example.learn.design.patterns.proxy.cglib;

/**
 * <p>
 * Cglib动态代理
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/3 16:50
 * @company 中再云图技术有限公司
 **/
public class CglibDynamicProxyClient {


    public static void main(String[] args) {
        CglibMatchmaker matchmaker = new CglibMatchmaker();
        Zhangsan instance = (Zhangsan)matchmaker.getInstance(Zhangsan.class);
        instance.findLove();
    }

}
