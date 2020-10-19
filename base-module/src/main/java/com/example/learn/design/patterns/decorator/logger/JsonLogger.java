package com.example.learn.design.patterns.decorator.logger;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 11:50
 * @company 中再云图技术有限公司
 **/
public class JsonLogger extends LoggerDecorator {

    public JsonLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String s) {
        JSONObject result = newJSONObject();
        result.put("message", s);
        logger.info(result.toJSONString());
    }

    @Override
    public void error(String s) {
        JSONObject result = newJSONObject();
        result.put("error", s);
        logger.info(result.toJSONString());
    }

    public void error(Exception ex) {
        JSONObject result = newJSONObject();
        result.put("exception", ex.getClass().getName());
        result.put("trace", Arrays.toString(ex.getStackTrace()));
        logger.error(result.toJSONString());
    }

    public JSONObject newJSONObject(){
        return new JSONObject();
    }
}
