package com.example.learn.leetcode.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 数组a中的数据，降序存储在b数组中
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/9/14 22:16
 **/
public class DescendArray {

    static int size = 10;
    static int[] a = new int[size];
    static int[] b = new int[size];

    static {
        for (int i = 0; i < size; i++) {
            a[i] = i;
        }
    }

    public static void main(String[] args) {
        for (int i = a.length - 1, counter = 0; i > 0; i--, counter ++) {
            b[counter] = a[i];
        }

        System.out.println(Arrays.toString(b));
    }
}
