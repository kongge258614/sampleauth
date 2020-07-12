package com.lkker.sampleauth.dao;

import com.lkker.sampleauth.entity.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

/**
 * @Author liliang
 * @Date 2020/7/12 18:17
 * @Description
 **/
@Component
public interface AuthPermissionRepository extends JpaRepository<AuthPermission,String>, JpaSpecificationExecutor<AuthPermission> {

    AuthPermission findAuthPermissionByUrl(String url);


}
