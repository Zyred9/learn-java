package com.example.jvm;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *  <p>
 * 堆溢出
 *  <p>
 * -Xmx20M(堆最大20兆)  -Xms20M(最小20M)
 *  <p>
 * @author zyred
 * @since v 0.1
 **/
@RestController
public class HeapOOM {

    static List<Object> list = new ArrayList<>();

    /**
     * java.lang.OutOfMemoryError: Java heap space
     */
    public static void heap() {
        while (true) {
            list.add(new Object());
        }
    }


    public static void main(String[] args) {
        heap();
    }
}
