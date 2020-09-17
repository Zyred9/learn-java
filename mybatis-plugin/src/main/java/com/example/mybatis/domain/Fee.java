package com.example.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/16 11:14
 **/
@Setter
@Getter
@ToString
public class Fee {

    private int id;

    private Double feeAmt;

    private Date feeDate;

}
