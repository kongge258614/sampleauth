package com.lkker.sampleauth.dao;

import com.lkker.sampleauth.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

/**
 * @Author liliang
 * @Date 2020/7/12 21:59
 * @Description
 **/
@Component
public interface AuthRoleRepository extends JpaRepository<AuthRole,String>, JpaSpecificationExecutor<AuthRole> {

}
