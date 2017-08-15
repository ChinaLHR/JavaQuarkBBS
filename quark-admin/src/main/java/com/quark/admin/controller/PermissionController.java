package com.quark.admin.controller;

import com.quark.admin.base.BaseController;
import com.quark.admin.dto.QuarkAdminResult;
import com.quark.admin.service.PermissionService;
import com.quark.common.entity.Permission;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lhr on 17-8-3.
 */
@RestController
@RequestMapping("/permissions")
public class PermissionController extends BaseController{
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/loadMenu")
    public List<Permission> loadMenu(){
        Integer userid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("AdminSessionId");
        List<Permission> list = permissionService.loadUserPermissionByType(userid,1);
        return list;
    }

    @PostMapping("/PermissionWithSelected")
    public QuarkAdminResult PermissionWithSelected(Integer roleId){
        QuarkAdminResult result = restProcessor(() -> {
            List<Permission> data = permissionService.findPermissionsAndSelected(roleId);
            return QuarkAdminResult.ok(data);
        });

        return result;
    }

}
