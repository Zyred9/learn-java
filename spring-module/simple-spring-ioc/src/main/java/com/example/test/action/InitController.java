package com.example.test.action;

import com.example.spring.framework.annotation.Autowired;
import com.example.spring.framework.annotation.Controller;
import com.example.spring.framework.annotation.RequestMapping;
import com.example.spring.framework.annotation.RequestParam;
import com.example.spring.framework.mvc.servlet.ModelAndView;
import com.example.test.service.UserService;
import com.example.test.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 公布接口url
 */
@Controller
@RequestMapping("/web")
public class InitController {

    @Autowired("userServiceImpl")
    private UserService userService;

    @RequestMapping("test")
    public ModelAndView first(@RequestParam("teacher") String teacher){
        Map<String,Object> model = new HashMap<>();
        model.put("teacher", teacher);
        model.put("data", this.userService.getData());
        model.put("token", UUID.randomUUID());
        return new ModelAndView("first", model);
    }

}
