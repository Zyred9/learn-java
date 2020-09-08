package com.example.socket.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <p>
 *      spring-socket 配置类
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/8 13:51
 * @company 中再云图技术有限公司
 **/
@Configuration
public class MySocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
