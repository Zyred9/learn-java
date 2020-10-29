package com.example.single;

import com.example.common.rest.HttpResponseEntity;
import com.example.common.rest.RespUtils;
import com.example.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@CrossOrigin
@RestController
@RequestMapping("/single")
public class SingleLoginController {

    @Autowired
    private SingleLoginService singleLoginService;

    @CrossOrigin
    @PostMapping("/login")
    public HttpResponseEntity login (@RequestBody User user) {
        return RespUtils.ok(this.singleLoginService.login(user));
    }

}
