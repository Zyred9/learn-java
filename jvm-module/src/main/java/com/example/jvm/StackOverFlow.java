package com.example.jvm;

/**
 * <p>
 *      栈溢出   java.lang.StackOverflowError
 *
 *      -Xss120K  设置栈大小
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class StackOverFlow {

    private static int a = 0;

    private static void over(int i){
        System.out.println(a ++);
        over(a);
    }

    public static void main(String[] args) {
        over(1);
    }

}
