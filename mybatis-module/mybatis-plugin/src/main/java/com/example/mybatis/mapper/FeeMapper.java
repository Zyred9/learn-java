package com.example.mybatis.mapper;

import com.example.mybatis.domain.Fee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/16 11:35
 * @company 中再云图技术有限公司
 **/
@Mapper
public interface FeeMapper {

    List<Fee> selectFee(Fee fee);

}
