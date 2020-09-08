package com.example.learn.design.patterns.decorator.logger;

import org.slf4j.Logger;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 13:53
 * @company 中再云图技术有限公司
 **/
public class LoggerDecoratorClient {

    private static final Logger logger = JsonLoggerFactory.getLogger(LoggerDecoratorClient.class);

    public static void main(String[] args) {
        logger.error("系统错误");
    }


}
