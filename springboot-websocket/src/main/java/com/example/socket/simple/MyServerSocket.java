package com.example.socket.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/8 10:44
 * @company 中再云图技术有限公司
 **/
@ServerEndpoint("/server/{userId}")
public class MyServerSocket {

    private Logger logger = LoggerFactory.getLogger(MyServerSocket.class);

    private String userId;

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.userId = userId;
        logger.debug("新连接：{}", userId);
    }

    @OnClose
    public void onClose() {
        logger.debug("连接：{} 关闭", this.userId);
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.debug("收到用户{}的消息{}", this.userId, message);
            //回复用户
            session.getBasicRemote().sendText("收到 " + this.userId + " 的消息 ");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        logger.debug("用户id为：{}的连接发送错误",this.userId);
        error.printStackTrace();
    }

}
