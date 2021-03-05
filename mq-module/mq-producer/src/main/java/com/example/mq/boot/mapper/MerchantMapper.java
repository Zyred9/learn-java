package com.example.mq.boot.mapper;

import com.example.mq.boot.entity.Merchant;

import java.util.List;

public interface MerchantMapper {

    Merchant getMerchantById(Integer sid);

    List<Merchant> getMerchantList(String name, int page, int limit);

    int add(Merchant merchant);

    int update(Merchant merchant);

    int updateState(Merchant merchant);

    int delete(Integer sid);

    int getMerchantCount();
}