package com.example.ibatis.mapper;


import com.example.ibatis.doman.Blog;

/**
 * @author 86183
 */
public interface BlogMapper {

    /**
     * 获取分页
     * @param id 电话
     * @return
     */
    Blog selectById(int id);
}
