package com.example.learn.design.patterns.prototype;

import com.example.learn.design.patterns.prototype.deep.DeepEntity;
import com.example.learn.design.patterns.prototype.shallow.Entity;
import com.example.learn.design.patterns.prototype.shallow.ShallowClone;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     原型模式：对象的克隆
 *
 *     适用场景：
 *              初始化小号资源比较多
 *              new 产生一个对象过程繁琐
 *              构造函数复杂
 *              循环过程中产生大量对象
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 16:19
 **/
public class PrototypeClient {

    public static void main(String[] args) {
        shallowClone();
    }


    /**
     * 浅克隆
     */
    public static void shallowClone (){
        DeepEntity entity = new DeepEntity();
        entity.setPassword("123456");
        entity.setUserName("zyred");
        
        List<String> hobbys = new ArrayList<>();
        hobbys.add("唱");
        hobbys.add("跳");
        hobbys.add("rap");
        hobbys.add("篮球");
        entity.setHobbys(hobbys);

        /*ShallowClone shallowClone = new ShallowClone();
        shallowClone.setUsername("李四");
        entity.setShallowClone(shallowClone);*/


        DeepEntity clone = entity.serializationDeepClone();
        clone.getHobbys().add("蔡徐坤");
        /*ShallowClone cloneShallowClone = clone.getShallowClone();
        cloneShallowClone.setUsername("王五");*/

        System.out.println("entity: " + entity);
        System.out.println("clone: " + clone);

        System.out.println(entity.getHobbys() == clone.getHobbys());
//        System.out.println(entity.getShallowClone() == clone.getShallowClone());
    }


    /*public static void main(String[] args) {
        ShallowClone shallowClone = new ShallowClone();
        shallowClone.setUsername("张三");
        List<String> hobbys = new ArrayList<>();
        hobbys.add("唱");
        hobbys.add("跳");
        hobbys.add("rap");
        hobbys.add("篮球");
        shallowClone.setHobbys(hobbys);

        ShallowClone clone = (ShallowClone)shallowClone.clone();
        clone.setUsername("李四");
        List<String> cloneHobbys = clone.getHobbys();
        cloneHobbys.add("蔡徐坤");

        System.out.println(shallowClone);
        System.out.println(clone);
    }*/

}
