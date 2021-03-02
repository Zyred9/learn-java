package com.example.prc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *          V2版本测试启动类
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.prc.v2"})
public class RpcClientApplicationV2 {

    public static void main(String[] args) {
        SpringApplication.run(RpcClientApplicationV2.class, args);
    }

}
