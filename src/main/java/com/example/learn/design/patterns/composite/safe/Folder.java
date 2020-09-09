package com.example.learn.design.patterns.composite.safe;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *      Linux 文件夹 - 根节点
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/9 14:15
 **/
public class Folder extends Directory {

    private Integer level;

    private List<Directory> dirs;

    public Folder(String name, int level) {
        super(name);
        this.level = level;
        this.dirs = new ArrayList<>();
    }

    @Override
    public void show() {
        System.out.println(this.name);

        for (Directory dir : dirs) {
            if (this.level != null) {
                for (Integer i = 0; i < this.level; i++) {
                    System.out.print("   ");
                }
                for (Integer i = 0; i < this.level; i++) {
                    if (i == 0) {
                        System.out.print("+");
                    }
                    System.out.print("-");
                }
            }
            dir.show();
        }
    }

    public Folder add(Directory dir) {
        this.dirs.add(dir);
        return this;
    }

    public void remove(Directory dir) {
        this.dirs.remove(dir);
    }

    public Directory get(int index) {
        return this.dirs.get(index);
    }

}
