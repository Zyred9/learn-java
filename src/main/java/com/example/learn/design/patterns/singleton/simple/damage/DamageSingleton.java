package com.example.learn.design.patterns.singleton.simple.damage;

import com.example.learn.design.patterns.singleton.simple.create.doublecheck.DoubleCheckSingleton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * <p>
 * 序列化和反序列化破坏单例
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 9:47
 **/
public class DamageSingleton {

    public static void main(String[] args) {
//        reflectDamageSingleton();
        serializationDamageSingleton();
    }

    /**
     * 反射破坏单例
     */
    public static void reflectDamageSingleton() {
        try {
            Constructor<DoubleCheckSingleton> constructor = DoubleCheckSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            DoubleCheckSingleton doubleCheckSingleton1 = constructor.newInstance();
            DoubleCheckSingleton doubleCheckSingleton2 = constructor.newInstance();

            // false
            System.out.println(doubleCheckSingleton1 == doubleCheckSingleton2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 序列化和反序列化破坏单例
     */
    public static void serializationDamageSingleton() {
        try {
            DoubleCheckSingleton doubleLock = DoubleCheckSingleton.getInstance();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("doubleLock_obj"));
            oos.writeObject(doubleLock);

            File file = new File("doubleLock_obj");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            DoubleCheckSingleton newDoubleLock = (DoubleCheckSingleton) ois.readObject();

            System.out.println(doubleLock);
            System.out.println(newDoubleLock);
            System.out.println(doubleLock == newDoubleLock);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
