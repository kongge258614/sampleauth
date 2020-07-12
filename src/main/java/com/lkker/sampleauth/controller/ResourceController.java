package com.lkker.sampleauth.controller;

import com.lkker.sampleauth.common.annotation.ApiDescription;
import com.lkker.sampleauth.model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liliang
 * @Date 2020/7/4 16:14
 * @Description
 **/
@RestController
@RequestMapping("/api")
public class ResourceController {

    @ApiDescription("查询用户信息")
    @RequestMapping("/userinfo")
    public UserInfo getUserInfo(){
        UserInfo userInfo = new UserInfo("LX1001","liliang","15810072844");
        return  userInfo;
    }


}
