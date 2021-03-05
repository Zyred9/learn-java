package com.example.mq.boot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mq.boot.entity.Merchant;
import com.example.mq.boot.mapper.MerchantMapper;
import com.example.mq.boot.service.MerchantService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qingshan
 * @Description: 咕泡学院，只为更好的你
 */
@Service
public class MerchantServiceImpl implements MerchantService {


    @Value("${com.test.topic_exchange}")
    private String topicExchange;

    @Value("${com.test.topic_routing_key1}")
    private String topicRoutingKey;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    AmqpTemplate rabbitTemplate;


    @Override
    public List<Merchant> getMerchantList(String name, int page, int limit) {
        return merchantMapper.getMerchantList(name, page, limit);
    }

    @Override
    public Merchant getMerchantById(Integer id) {
        return merchantMapper.getMerchantById(id);
    }

    @Override
    public int add(Merchant merchant) {
        int k = merchantMapper.add(merchant);
        System.out.println("aaa : " + merchant.getId());
        JSONObject title = new JSONObject();
        String jsonBody = JSONObject.toJSONString(merchant);
        title.put("type", "add");
        title.put("desc", "新增商户");
        title.put("content", jsonBody);
        rabbitTemplate.convertAndSend(topicExchange, topicRoutingKey, title.toJSONString());

        return k;
    }

    @Override
    public int updateState(Merchant merchant) {

        int k = merchantMapper.updateState(merchant);

        JSONObject title = new JSONObject();
        String jsonBody = JSONObject.toJSONString(merchant);
        title.put("type", "state");
        title.put("desc", "更新商户状态");
        title.put("content", jsonBody);
        rabbitTemplate.convertAndSend(topicExchange, topicRoutingKey, title.toJSONString());

        return k;
    }

    @Override
    public int update(Merchant merchant) {

        int k = merchantMapper.update(merchant);

        // 发送消息失败了？？？
        JSONObject title = new JSONObject();
        String jsonBody = JSONObject.toJSONString(merchant);
        title.put("type", "update");
        title.put("desc", "更新商户信息");
        title.put("content", jsonBody);
        rabbitTemplate.convertAndSend(topicExchange, topicRoutingKey, title.toJSONString());

        return k;
    }

    @Override
    public int delete(Integer id) {

        int k = merchantMapper.delete(id);

        JSONObject title = new JSONObject();
        Merchant merchant = new Merchant();
        merchant.setId(id);
        String jsonBody = JSONObject.toJSONString(merchant);
        title.put("type", "delete");
        title.put("desc", "删除商户");
        title.put("content", jsonBody);

        rabbitTemplate.convertAndSend(topicExchange, topicRoutingKey, title.toJSONString());

        return k;
    }

    @Override
    public int getMerchantCount() {

        return merchantMapper.getMerchantCount();
    }
}
