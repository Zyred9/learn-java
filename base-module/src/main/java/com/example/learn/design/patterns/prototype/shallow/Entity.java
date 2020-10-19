package com.example.learn.design.patterns.prototype.shallow;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 *      实体类，实现 JDK 自带的 Cloneable 接口，完成对象的浅克隆
 *
 *      浅克隆： Jdk 对字节码进行复制，但是对实体类中引用无法重新创建，如果实体类中
 *      存在对其他对象的引用，那么原对象一旦被 clone 后，clone出来的修改其中引用的值
 *      那么原对象被引用的值也会发生改变
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 16:33
 **/
@Getter
@Setter
@ToString
public class Entity implements Cloneable{

    private String userName;

    private String password;

    private List<String> phone;

    private ShallowClone shallowClone;

    @Override
    public Entity clone (){
        try {
            return (Entity)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
