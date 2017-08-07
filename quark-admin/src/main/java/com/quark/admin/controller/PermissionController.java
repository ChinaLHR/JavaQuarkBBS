package com.quark.admin.controller;

import com.quark.admin.service.PermissionService;
import com.quark.common.entity.Permission;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
        Integer userid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("AdminSessionId");
        List<Permission> list = permissionService.loadUserPermissionByType(userid,1);
        return list;
    }
}
