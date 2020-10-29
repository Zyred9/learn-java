package com.example.circular;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class Assert {

    public static void isNotEmpty(String ... str){
        for (String s : str) {
            if (s == null || Objects.equals("", s)) {
                throw new RuntimeException("字段为空");
            }
        }
    }

}
