package com.example.learn.design.patterns.prototype.shallow;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 浅克隆
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 16:34
 **/
@Setter
@Getter
@ToString
public class ShallowClone implements Serializable, Cloneable {
    private String username;
    List<String> hobbys;

    @Override
    public Object clone() {
        try {

            Object clone = super.clone();
            return clone;
        } catch (Exception ex) {
            return null;
        }
    }
}
