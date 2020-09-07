package com.example.learn.design.patterns.flyweight;

/**
 * <p>
 *      String 类对享元模式的使用。
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/9/7 22:15
 **/
public class StringFlyweight {

    public static void main(String[] args) {

        String s1 = "hello";
        String s2 = "he" + "llo";
        String s3 = "he";
        String s4 = "llo";
        String s5 = s3 + s4;

        //  true, JDK只对常量的加法做了优化处理，但是没有对定义常量的变量进行处理
        System.out.println(s1 == s2);
        // false， 这里  s5 是由s3变量加上s4变量得到的，所以，s5不等于 s1
        System.out.println(s1 == s5);
    }

}
