package com.example.learn.socket.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 * @Class: WebSocketMessageService
 * @Description: 使用webscoket连接向用户发送信息
 */
@Service("webSocketMessageService")
public class WebMessageService {
    private Logger logger = LoggerFactory.getLogger(WebMessageService.class);
    /**
     * 声明websocket连接类
     **/
    private MvcWebSocket websocketDemo = new MvcWebSocket();

    /**
     * @param @param  userId 用户id
     * @param @param  message 消息
     * @param @return 发送成功返回true，否则返回false
     * @Title: sendToAllTerminal
     * @Description: 调用websocket类给用户下的所有终端发送消息
     */
    public Boolean sendToAllTerminal(Long userId, String message) {
        logger.debug("向用户{}的消息：{}", userId, message);
        if (websocketDemo.sendMessageToUser(userId, message)) {
            return true;
        } else {
            return false;
        }
    }
}
