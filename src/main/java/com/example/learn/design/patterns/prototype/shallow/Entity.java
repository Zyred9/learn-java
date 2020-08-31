package com.example.learn.design.patterns.prototype.shallow;

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
public class Entity implements Cloneable{

    private String userName;

    private String password;

    private List<String> phone;

    private ShallowClone shallowClone;

    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public ShallowClone getShallowClone() {
        return shallowClone;
    }

    public void setShallowClone(ShallowClone shallowClone) {
        this.shallowClone = shallowClone;
    }

    @Override
    public Entity clone (){
        try {
            return (Entity)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Entity{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", shallowClone=" + shallowClone +
                '}';
    }
}
