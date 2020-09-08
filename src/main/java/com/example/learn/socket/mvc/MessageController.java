package com.example.learn.socket.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 86183
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);
    /**
     * websocket服务层调用类
     **/
    @Autowired
    private WebMessageService wsMessageService;

    /**
     * 请求入口
     *
     * @param userId
     * @param message
     * @return
     */
    @ResponseBody
    @GetMapping("/getMsg")
    public String TestWS(@RequestParam(value = "userId", required = true) Long userId,
                         @RequestParam(value = "message", required = true) String message) {
        logger.debug("收到发送请求，向用户{}的消息：{}", userId, message);
        if (wsMessageService.sendToAllTerminal(userId, message)) {
            return "发送成功";
        } else {
            return "发送失败";
        }
    }
}