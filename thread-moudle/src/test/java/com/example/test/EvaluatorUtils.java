package com.example.test;


import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class EvaluatorUtils {

    public static void main(String[] args) {
        Clibrary.INSTANCE.sc();
//        Clibrary.INSTANCE.printf("Hello, World");
    }


    public interface Clibrary extends Library {
        Clibrary INSTANCE = (Clibrary) Native.loadLibrary("D:\\test.dll", Clibrary.class);
        int sc();
        int printf(String str);
    }
}