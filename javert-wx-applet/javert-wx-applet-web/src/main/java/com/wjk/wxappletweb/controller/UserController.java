package com.wjk.wxappletweb.controller;

import com.wjk.wxappletweb.service.impl.UserServiceImpl;
import com.wjk.wxappletweb.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjunkai
 * @description
 * @date 2023/5/18 13:56
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/login")
    public String login(User user){
        return "";
    }

    @RequestMapping("/key")
    public String loginByKey(User user){
        return "";
    }
}
