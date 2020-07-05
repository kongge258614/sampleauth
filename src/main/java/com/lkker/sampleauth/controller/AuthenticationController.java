package com.lkker.sampleauth.controller;

import com.lkker.sampleauth.entity.AuthInfo;
import com.lkker.sampleauth.service.impl.AuthInServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liliang
 * @Date 2020/7/4 16:46
 * @Description
 **/

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthInServiceImpl authInServiceImpl;

    //用户注册
    @PostMapping("/signup")
    public void signup(@RequestBody AuthInfo authInfo){
        authInServiceImpl.signup(authInfo);
    }
}
