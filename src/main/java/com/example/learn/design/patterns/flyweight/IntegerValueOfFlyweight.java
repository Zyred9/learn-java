package com.example.learn.design.patterns.flyweight;

/**
 * <p>
 *          Integer 方法对享元模式的使用
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/9/7 22:23
 **/
public class IntegerValueOfFlyweight {

    /**
     * public static Integer valueOf(int i) {
     *       IntegerCache.low = -128;
     *       IntegerCache.high = 127
     *     if (i >= IntegerCache.low && i <= IntegerCache.high)
     *         return IntegerCache.cache[i + (-IntegerCache.low)];
     *     return new Integer(i);
     * }
     * @param args
     */
    public static void main(String[] args) {
        Integer i1 = 127;
        Integer i2 = Integer.valueOf(127);

        Integer i3 = 128;
        Integer i4 = Integer.valueOf(128);

        // true
        System.out.println(i1 == i2);
        // false
        System.out.println(i3 == i4);
    }

}
