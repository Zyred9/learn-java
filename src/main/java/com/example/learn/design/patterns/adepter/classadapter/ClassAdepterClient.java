package com.example.learn.design.patterns.adepter.classadapter;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/9 18:01
 * @company 中再云图技术有限公司
 **/
public class ClassAdepterClient {


    public static void main(String[] args) {
        // 这里既能用适配器接收，也能用适配出来的电压接收，违背了最少知道原则
//        DC5 adapter = new Adapter();
        Adapter adapter = new Adapter();
        int dc5v = adapter.output5v();
        System.out.println("交流电" + adapter.output220v() + "V转换为直流电" + dc5v + "v");
    }

}
