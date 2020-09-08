package com.example.socket.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 86183
 */
@RestController
@RequestMapping("/api/ws")
public class WebSocketController {


    /**
     * 群发消息内容
     *
     * @param message
     * @return
     */
    @GetMapping("sendAll")
    public String sendAllMessage(@RequestParam String message) {
        try {
            WebSocketServer.BroadCastInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 指定会话ID发消息
     *
     * @param message 消息内容
     * @param userId  连接会话ID
     * @return
     */
    @GetMapping(value = "/sendOne")
    public String sendOneMessage(@RequestParam String message, @RequestParam String userId, @RequestParam String userName) {
        MessageEntity messageEntity = new MessageEntity()
                .setMessage(message)
                .setUserId(userId)
                .setUserName(userName)
                .setTime();
        WebSocketServer.SendMessage(messageEntity);
        return "ok";
    }
}