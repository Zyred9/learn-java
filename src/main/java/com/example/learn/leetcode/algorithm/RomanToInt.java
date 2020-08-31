package com.example.learn.leetcode.algorithm;

/**
 * <p>
 * 罗马数字转换为 int
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/28 14:43
 **/
public class RomanToInt {

    private static int getMapping(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static int romanToInt(String s) {
        int sum = 0;

        int first = getMapping(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int turn = getMapping(s.charAt(i));
            if (first < turn) {
                sum -= first;
            } else {
                sum += first;
            }
            first = turn;
        }
        return sum += first;
    }


    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }

}
