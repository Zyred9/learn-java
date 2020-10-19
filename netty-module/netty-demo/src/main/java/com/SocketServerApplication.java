package com;

import com.com.netty.firstestexample.TestServer;
import com.server.Server;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class SocketServerApplication implements CommandLineRunner {

    @Autowired
    Server server;

    //	数据查询
    private int port1 = 1231;

    public static void main(String[] args) {

        //不启动web服务，同时好像tomcat也不运行了
        SpringApplication app = new SpringApplication(SocketServerApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }


    @Override
    public void run(String... args) throws Exception {

        ChannelHandler[] acceptorHandlers1 = new ChannelHandler[2];

        this.server.start(port1, acceptorHandlers1);
    }
}