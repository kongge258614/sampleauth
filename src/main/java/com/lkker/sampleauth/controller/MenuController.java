package com.lkker.sampleauth.controller;

import com.lkker.sampleauth.common.annotation.ApiDescription;
import com.lkker.sampleauth.common.utils.BaseMessage;
import com.lkker.sampleauth.service.ApiMenuAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class MenuController {

    @Autowired
    private ApiMenuAssetService apiMenuAssetService;

    @ApiDescription("同步系统所有权限")
    @PostMapping("/user/permission/sync")
    public BaseMessage syncUserPermission() {
        apiMenuAssetService.saveAllUrl();
        return BaseMessage.SUCCESS;
    }
}
