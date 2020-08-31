package com.example.learn.es;

import org.springframework.data.domain.Page;

import java.util.List;

public interface EsService {

    /**
     * 插入
     */
    void add(Student student);
    
    /**
     * 批量插入
     */
    void addAll(List<Student> student);

    /**
     * 模糊查询
     */
    Page<Student> search(String keyword, QueryPage queryPage);
}
