package com.example.learn.design.patterns.adepter.interfaceadapter;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 9:42
 * @company 中再云图技术有限公司
 **/
public class InterfaceAdapterClient {

    public static void main(String[] args) {
        AC220 ac220 = new AC220();
        InterfaceAdapter adapter = new InterfaceAdapter(ac220);
        System.out.println(adapter.output5v());
        System.out.println(adapter.output12v());
        System.out.println(adapter.output24v());
        System.out.println(adapter.output36v());
    }

}
