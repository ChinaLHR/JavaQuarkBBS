package com.quark.admin.controller;

import com.quark.admin.dto.QuarkAdminResult;
import com.quark.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LHR
 * Create By 2017/8/12
 */
@RestController
@RequestMapping("roles")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/rolesWithSelected")
    public QuarkAdminResult rolesWithSelected(Integer uid){
        QuarkAdminResult result = roleService.findRolesAndSelected(uid);
        return result;
    }

}
