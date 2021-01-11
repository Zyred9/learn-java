package com.example.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * <p>
 * 指针压缩
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class PointCompress {

    /**
     * 输出结果：
     *
     * hashCode: 856419764
     * com.example.jvm.Book object internals:
     *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
     *       0     4                    (object header)                           01 b4 ed 0b (00000001 10110100 11101101 00001011) (200127489)
     *       4     4                    (object header)                           33 00 00 00 (00110011 00000000 00000000 00000000) (51)
     *       8     4                    (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
     *      12     4                int Book.lenth                                0
     *      16     4   java.lang.String Book.name                                 null
     *      20     4   java.lang.String Book.witdh                                null
     * Instance size: 24 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     * @param args
     */
    public static void main(String[] args) {
        Book book = new Book();
        System.out.println(book);
        System.out.println(book.hashCode());
        book.printf(book);
    }
}


class Book {

    private String name;

    private int lenth;

    private String witdh;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLenth() {
        return lenth;
    }

    public void setLenth(int lenth) {
        this.lenth = lenth;
    }

    public String getWitdh() {
        return witdh;
    }

    public void setWitdh(String witdh) {
        this.witdh = witdh;
    }

    // 查看对象的整体结构信息 // JOL工具类
    public static void printf(Book p) {
        System.out.println(ClassLayout.parseInstance(p).toPrintable());
    }
}
