package com.example.prc.v2.controller;

import com.exampl.rpc.v1.service.IOrderService;
import com.exampl.rpc.v1.service.IUserService;
import com.example.prc.v2.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *          rpc 集成spring测试
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@RestController
@RequestMapping("/rpc")
public class TestRpcController {

    @Reference
    private IUserService userService;

    @Reference
    private IOrderService orderService;

    public TestRpcController() {
    }


    @GetMapping("/getUserList")
    public String getUserList(){
        return this.userService.getUserList();
    }

    @GetMapping("/getUserById/{id}")
    public String getUserList(@PathVariable int id){
        return this.userService.getUserById(id);
    }

    @GetMapping("/allOrders")
    public String allOrders(){
        return this.orderService.allOrders();
    }



}
