package com.example.learn.design.patterns.adepter.objctadapter;

/**
 * <p>
 *      对象适配器客户端
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 9:31
 **/
public class ObjectAdepterClient {

    public static void main(String[] args) {
        // 这里ObjectAdapter就满足了最少知道原则
        AC220 ac220 = new AC220();
        ObjectAdapter adapter = new ObjectAdapter(ac220);
        int dc5v = adapter.output5v();
        System.out.println("交流电" + ac220.output220v() + "V转换为直流电" + dc5v + "v");
    }


}
