package com.lkker.sampleauth.controller;

import com.lkker.sampleauth.model.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liliang
 * @Date 2020/7/4 16:14
 * @Description
 **/
@RestController
@RequestMapping("/user")
public class ResourceController {

    @RequestMapping("/userinfo")
    public UserInfo getUserInfo(){
        UserInfo userInfo = new UserInfo("LX1001","liliang","15810072844");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(principal);
        return  userInfo;
    }
}
