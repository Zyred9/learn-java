package com.example.learn.design.patterns.prototype.deep;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.example.learn.design.patterns.prototype.shallow.ShallowClone;

import java.io.*;
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
public class DeepEntity implements Cloneable, Serializable {

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
    public DeepEntity clone (){
        try {
            return (DeepEntity)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     *  序列化和反序列化深克隆
     *  性能低， 占用IO，
     *
     * @return
     */
    public DeepEntity serializationDeepClone (){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (DeepEntity)ois.readObject();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *  FastJson 序列化和反序列化深克隆
     *
     * @return
     */
    public DeepEntity JsonDeepClone (){
        String json = JSON.toJSONString(this);
        return JSON.parseObject(json, DeepEntity.class);
    }

    /**
     * 使用spring-framework 工具类实现深克隆
     * @return
     */
    public DeepEntity beanUtilDeepClone() {
        DeepEntity deepEntity = new DeepEntity();
        BeanUtil.copyProperties(this, deepEntity, true);
        return deepEntity;
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
