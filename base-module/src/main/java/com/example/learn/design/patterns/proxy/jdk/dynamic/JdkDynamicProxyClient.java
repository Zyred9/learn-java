package com.example.learn.design.patterns.proxy.jdk.dynamic;

/**
 * <p>
 *      JDK 动态代理客户端
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/1 9:06
 **/
public class JdkDynamicProxyClient {

    public static void main(String[] args) {
        JdkMatchmaker matchmaker = new JdkMatchmaker();
        IPerson lisi = matchmaker.getInstance(new Lisi());
        lisi.findLove();
        lisi.buyInsure();

        System.out.println("==========");

        IPerson zhansan = matchmaker.getInstance(new Zhangsan());
        zhansan.buyInsure();
        zhansan.findLove();
    }

}
