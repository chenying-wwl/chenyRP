package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@RestController
public class LoginController {
    @PostMapping("login.action")
    public void login(String username,String password){

    }
}
