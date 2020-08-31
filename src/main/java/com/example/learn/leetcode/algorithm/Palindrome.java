package com.example.learn.leetcode.algorithm;

/**
 * <p>
 * https://leetcode-cn.com/problems/palindrome-number/
 * <p>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * 输入: 121
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/28 13:49
 **/
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
    }

    /**
     * 折半算法，出入 1221
     * 思路：将一个数字拆分成两段，比较前后段是否相等
     * 第一次拆分：1221 ->  122, 1
     * 第二次拆分：122  ->  12,  2
     * <p>
     * 拆分后的数据累加
     * 第一次累加： 0 * 10 + 1 = 1
     * 第二次累加： 1 * 10 + 2 = 12
     * <p>
     * 12 = 12 true
     * <p>
     * 传入 3位数：121
     * 第一次拆分：121 -> 12, 1
     * 第二次拆分: 12  -> 1,  2
     * <p>
     * 第一次累加：0 * 10 + 1 = 1
     * 第二次累加：1 * 10 + 2 = 12
     * <p>
     * 1 == 12 false  || 1 == 12 / 10 true  ==>  true
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int y = 0;
        while (x > y) {
            int last = x % 10;
            x /= 10;
            y = y * 10 + last;
        }
        return x == y || x == y / 10;
    }


}
