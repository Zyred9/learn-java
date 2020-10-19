package com.example.action;

import com.example.spring.framework.annotation.Autowired;
import com.example.spring.framework.annotation.Controller;
import com.example.spring.framework.annotation.RequestMapping;
import com.example.test.service.OrderService;
import com.example.test.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公布接口url
 *
 * @author Tom
 */
@Controller
@RequestMapping("/web")
public class InitController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @RequestMapping("test")
    public void getUsers(HttpServletResponse response) {
        String byUserId = orderService.getOrderByUserId("123");
        this.out(response, byUserId);
    }

    private void out(HttpServletResponse resp, String str) {
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
