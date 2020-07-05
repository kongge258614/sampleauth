package com.lkker.sampleauth.service;


import com.lkker.sampleauth.dao.AuthInfoRepository;
import com.lkker.sampleauth.entity.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liliang
 * @Date 2020/7/4 12:30
 * @Description
 **/
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthInfoRepository authInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        AuthInfo authInfo = authInfoRepository.findAuthInfoById(id);
        if(authInfo != null){
            // 处理权限
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("admin");
            //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
            grantedAuthorities.add(grantedAuthority);
            return new User(id,authInfo.getPassword(), grantedAuthorities);
        }else {
            throw new UsernameNotFoundException("error: " + authInfo.getId() + " do not exist!");
        }
    }
}
