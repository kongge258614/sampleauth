package com.lkker.sampleauth.service;

import com.lkker.sampleauth.common.annotation.ApiDescription;
import com.lkker.sampleauth.common.utils.IdGenerator;
import com.lkker.sampleauth.dao.AuthPermissionRepository;
import com.lkker.sampleauth.entity.AuthPermission;
import com.lkker.sampleauth.entity.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ApiMenuAssetService {

    private final static Logger logger = LoggerFactory.getLogger(ApiMenuAssetService.class);

    @Autowired
    private AuthPermissionRepository authPermissionRepository;


    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Transactional
    public String saveAllUrl() {

        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()) {
            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            final HandlerMethod method = map.get(info);
            for (String url : patterns) {
                final ApiDescription apiDescription = method.getMethodAnnotation(ApiDescription.class);
                if (apiDescription != null) {
                    AuthPermission authPermission = authPermissionRepository.findAuthPermissionByUrl(transformUrl2Permission(url));
                    if (authPermission == null){
                        final AuthPermission permission = new AuthPermission();
                        permission.setUrl(transformUrl2Permission(url));
                        permission.setDescription(apiDescription.value());
                        permission.setEditable(false);
                        permission.setIsPublic(apiDescription.isPublic());
                        permission.setId(IdGenerator.getNextId(AuthPermission.class));
                        authPermissionRepository.save(permission);
                    }



                }
            }
        }
        logger.info("自动添加菜单完成！");
        return "";
    }




    public String transformUrl2Permission(String url) {

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : url.toCharArray()) {
            //暂时只做/ 转换为_
            if (c == '/') {
                //剔除多个符号
                if (stringBuilder.length() != 0 && stringBuilder.charAt(stringBuilder.length() - 1) != '_') {
                    stringBuilder.append('_');
                }
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
