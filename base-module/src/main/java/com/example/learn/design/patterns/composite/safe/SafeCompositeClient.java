package com.example.learn.design.patterns.composite.safe;

/**
 * <p>
 *      组合模式客户端：结构型模式
 *
 *      定义：通过单个对象和组合对象用相同的接口进行表示
 *
 *      作用：使客户端对单个对象和组合对象保持一致的方式处理
 *
 *      使用场景：
 *       1. 希望客户端可以忽略组合对象与单个对象的差异时；
 *       2. 对象层次具备整体和部分，呈树结构；
 *
 *
 *      安全型写法：
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/8 17:40
 **/
public class SafeCompositeClient {

    public static void main(String[] args) {
        File qq = new File("QQ");
        File wechat = new File("wechat");
        Folder tencent = new Folder("Tencent", 2).add(qq).add(wechat);

        File ppt = new File("ppt");
        File word = new File("word");
        File excel = new File("excel");

        Folder office = new Folder("office", 2).add(ppt).add(word).add(excel);

        Folder linux = new Folder("Linux", 1).add(tencent).add(office);
        linux.show();
    }
}
