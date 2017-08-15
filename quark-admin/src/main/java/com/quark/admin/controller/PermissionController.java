package com.quark.admin.controller;

import com.quark.admin.base.BaseController;
import com.quark.admin.dto.PageResult;
import com.quark.admin.dto.QuarkAdminResult;
import com.quark.admin.service.PermissionService;
import com.quark.admin.shiro.ShiroService;
import com.quark.common.entity.Permission;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lhr on 17-8-3.
 */
@RestController
@RequestMapping("/permissions")
public class PermissionController extends BaseController{
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ShiroService shiroService;

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

    @GetMapping
    public PageResult getAll(String draw,
                                         @RequestParam(required = false, defaultValue = "1") int start,
                                         @RequestParam(required = false, defaultValue = "10") int length){
        int pageNo = start/length;
        Page<Permission> page = permissionService.findByPage(pageNo, length);
        PageResult<List<Permission>> result = new PageResult<>(
                draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent());

        return result;
    }

    @PostMapping("/add")
    public QuarkAdminResult add(Permission permission) {
        QuarkAdminResult result = restProcessor(() -> {
            permissionService.save(permission);
            //更新权限
            shiroService.updatePermission();
            return QuarkAdminResult.ok();
        });
        return result;
    }

    @PostMapping("/delete")
    public QuarkAdminResult delete(@RequestParam(value = "id[]") Permission[] id){
        QuarkAdminResult result = restProcessor(() -> {
            permissionService.deleteInBatch(Arrays.asList(id));
            //更新权限
            shiroService.updatePermission();
            return QuarkAdminResult.ok();
        });
        return result;
    }

}
