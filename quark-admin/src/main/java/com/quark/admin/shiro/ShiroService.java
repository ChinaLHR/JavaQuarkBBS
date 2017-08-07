package com.quark.admin.shiro;

import com.quark.admin.service.PermissionService;
import com.quark.common.entity.Permission;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiroService {

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;


    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/font-awesome/**","anon");
        List<Permission> permissionList = permissionService.findAll();
        for(Permission p:permissionList){

            if (!StringUtils.isEmpty(p.getPerurl())) {
                String permission = "perms[" + p.getPerurl()+ "]";
                filterChainDefinitionMap.put(p.getPerurl(),permission);
            }
        }
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }
}
