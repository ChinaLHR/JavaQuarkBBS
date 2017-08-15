package com.quark.admin.controller;

import com.quark.admin.base.BaseController;
import com.quark.admin.dto.PageResult;
import com.quark.admin.dto.QuarkAdminResult;
import com.quark.admin.service.RoleService;
import com.quark.common.entity.Permission;
import com.quark.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author LHR
 * Create By 2017/8/12
 */
@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public PageResult getAll(String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length) {
        int pageNo = start / length;
        Page<Role> page = roleService.findByPage(pageNo, length);
        PageResult<List<Role>> result = new PageResult<>(
                draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent());

        return result;
    }

    @PostMapping("/rolesWithSelected")
    public QuarkAdminResult rolesWithSelected(Integer uid) {
        QuarkAdminResult result = roleService.findRolesAndSelected(uid);
        return result;
    }

    @PostMapping("/add")
    public QuarkAdminResult add(Role role) {
        QuarkAdminResult result = restProcessor(() -> {
            roleService.save(role);
            return QuarkAdminResult.ok();
        });
        return result;
    }

    @PostMapping("/delete")
    public QuarkAdminResult delete(@RequestParam(value = "id[]") Role[] id){
        QuarkAdminResult result = restProcessor(() -> {
            roleService.deleteInBatch(Arrays.asList(id));
            return QuarkAdminResult.ok();
        });

        return result;
    }

    @PostMapping("/saveRolePermission")
    public QuarkAdminResult saveRolePermission(Integer roleid, @RequestParam(value = "pers[]") Permission[] pers){

        QuarkAdminResult result = restProcessor(() -> {
            roleService.saveRolePermission(roleid, pers);
            return QuarkAdminResult.ok();
        });

        return result;
    }

}
