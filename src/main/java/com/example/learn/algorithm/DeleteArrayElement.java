package com.example.learn.algorithm;

/**
 * <p>
 *      有n个数组的元素，删除第pos位，并让数组发生位移
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/9/15 20:46
 **/
public class DeleteArrayElement {

    static int size = 5;
    static int [] a = new int[size];

    static {
        for (int i = 0; i < size; i++) {
            a[i] = i;
        }
    }

    public static void main(String[] args) {
        System.out.println(delete(a, 3));
    }

    protected static boolean delete(int[] a, int pos){
        int len = a.length;
        if (pos < 0 || pos > len){
            return false;
        }

        for (int i = pos + 1; i < len; i++) {
            a[i - 1] = a[i];
            return true;
        }
        return true;
    }

}
