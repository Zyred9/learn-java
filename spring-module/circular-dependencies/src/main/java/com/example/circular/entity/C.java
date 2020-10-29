package com.example.circular.entity;

import com.example.circular.annotation.Lazy;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class C {

    @Lazy
    private A a;


    public void useObject(){
        System.out.println("对象A被注入到C中：" + a.hashCode());
    }

}
