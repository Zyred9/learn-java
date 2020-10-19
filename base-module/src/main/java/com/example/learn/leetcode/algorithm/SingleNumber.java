package com.example.learn.leetcode.algorithm;

/**
 * <p>
 *      https://leetcode-cn.com/problems/single-number/
 *      给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/28 16:05
 **/
public class SingleNumber {

    public static int singleNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum  = sum ^ num;
        }
        return sum;
    }

    public static void main(String[] args) {
//        System.out.println(singleNumber(new int[]{1, 3, 4, 5, 5, 4, 1}));

        int i = 1 ^ 3 ^ 4 ^ 5 ^ 5 ^ 4 ^ 1;
        System.out.println(i);
    }

}
