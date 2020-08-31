package com.example.learn.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsRepository extends ElasticsearchRepository<Student, String> {

    /**
     * 根据学生姓名或信息模糊查询
     */
    Page<Student> findByNameAndInfoLike(String name, String info, Pageable pageable);
}
