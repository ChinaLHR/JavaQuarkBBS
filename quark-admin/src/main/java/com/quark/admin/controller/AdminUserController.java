package com.quark.admin.controller;

import com.quark.admin.service.AdminUserService;
import com.quark.common.base.BaseController;
import com.quark.common.dto.PageResult;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminUserController extends BaseController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 翻页获取管理员
     *
     * @param adminUser
     * @param draw:请求次数
     * @param start
     * @param length
     * @return
     */
    @GetMapping
    public PageResult getAll(AdminUser adminUser, String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length) {
        int pageNo = start / length;
        Page<AdminUser> page = adminUserService.findByPage(adminUser, pageNo, length);
        PageResult<List<AdminUser>> result = new PageResult<>(
                draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent());
        return result;
    }

    @PostMapping("/add")
    public QuarkResult addAdmin(AdminUser adminUser) {

        QuarkResult result = restProcessor(() -> {
            if (adminUserService.findByUserName(adminUser.getUsername()) != null)
                return QuarkResult.error("用户名重复");
            adminUserService.saveAdmin(adminUser);
            return QuarkResult.ok();
        });

        return result;
    }

    @PostMapping("/delete")
    public QuarkResult deleteAdmin(@RequestParam(value = "id[]") AdminUser[] id) {

        QuarkResult result = restProcessor(() -> {
            List<AdminUser> collect = Arrays.asList(id);
            adminUserService.deleteInBatch(collect);
            return QuarkResult.ok();
        });
        return result;
    }


    @PostMapping("/saveAdminRoles")
    public QuarkResult saveAdminRoles(Integer uid, Integer[] id) {

        QuarkResult result = restProcessor(() -> {
            adminUserService.saveAdminRoles(uid, id);
            return QuarkResult.ok();
        });
        return result;
    }

    @PostMapping("/saveAdminEnable")
    public QuarkResult saveAdminEnable(@RequestParam(value = "id[]") Integer[] id) {
        QuarkResult result = restProcessor(() -> {
            adminUserService.saveAdminEnable(id);
            return QuarkResult.ok();
        });
        return result;
    }

}
