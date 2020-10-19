package com.example.learn.design.patterns.decorator.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 13:54
 * @company 中再云图技术有限公司
 **/
public class JsonLoggerFactory {

    public static Logger getLogger(Class clazz){
        Logger logger = LoggerFactory.getLogger(clazz);
        return new JsonLogger(logger);
    }

}
