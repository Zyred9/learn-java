package com.example.learn.es;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *      es 插入实体类，到时候直接反序列化为本类就型
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/31 15:51
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EsEntity<T> implements Serializable {

    public String id;

    private T data;

}
