package com.example.learn.design.patterns.composite.safe;

/**
 * <p>
 *      Linux文件 - 叶子节点
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/9 14:13
 * @company 中再云图技术有限公司
 **/
public class File extends Directory{

    public File(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println(this.name);
    }
}
