package com.lkker.sampleauth.controller;

import com.lkker.sampleauth.common.annotation.ApiDescription;
import com.lkker.sampleauth.common.utils.BaseMessage;
import com.lkker.sampleauth.entity.AuthRole;
import com.lkker.sampleauth.service.ApiMenuAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private ApiMenuAssetService apiMenuAssetService;

    @ApiDescription("同步系统所有权限")
    @PostMapping("/user/permission/sync")
    public BaseMessage syncUserPermission() {
        apiMenuAssetService.saveAllUrl();
        return BaseMessage.SUCCESS;
    }

    /**
     * 创建用户角色
     */
    @ApiDescription("同步系统所有权限")
    @PostMapping("/user/role")
    public BaseMessage createRole(@RequestBody AuthRole authRole){

        return BaseMessage.SUCCESS;
    }


    /**
     * 创建用户权限组
     */

    /**
     * 为权限组赋权限
     */

    /**
     * 为用户赋角色
     */
}
