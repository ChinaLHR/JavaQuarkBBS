package com.quark.admin.controller;

import com.quark.admin.service.PermissionService;
import com.quark.common.entity.Permission;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhr on 17-8-3.
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @RequestMapping("/loadMenu")
    public List<Permission> loadMenu(){
        Map<String,Object> map = new HashMap<>();
        Integer userid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("AdminSessionId");
        map.put("type",1);
        map.put("userid",userid);
        //TODO　修改service方法，根据type获取
        List<Permission> list = permissionService.loadUserPermission(userid);
        return list;
    }
}
