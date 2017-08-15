package com.quark.admin.controller;

import com.quark.admin.base.BaseController;
import com.quark.admin.dto.PageResult;
import com.quark.admin.dto.QuarkAdminResult;
import com.quark.admin.enums.SqlErrorEnum;
import com.quark.admin.service.AdminUserService;
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
    public QuarkAdminResult addAdmin(AdminUser adminUser) {

        QuarkAdminResult result = restProcessor(() -> {
            if (adminUserService.findByUserName(adminUser.getUsername()) != null)
                return QuarkAdminResult.error(SqlErrorEnum.REPEATCOLUMN.getErrorMessage());
            adminUserService.saveAdmin(adminUser);
            return QuarkAdminResult.ok();
        });

        return result;
    }

    @PostMapping("/delete")
    public QuarkAdminResult deleteAdmin(@RequestParam(value = "id[]") AdminUser[] id) {

        QuarkAdminResult result = restProcessor(() -> {
            List<AdminUser> collect = Arrays.asList(id);
            adminUserService.deleteInBatch(collect);
            return QuarkAdminResult.ok();
        });
        return result;
    }


    @PostMapping("/saveAdminRoles")
    public QuarkAdminResult saveAdminRoles(Integer uid, Integer[] id) {

        QuarkAdminResult result = restProcessor(() -> {
            adminUserService.saveAdminRoles(uid, id);
            return QuarkAdminResult.ok();
        });
        return result;
    }

    @PostMapping("/saveAdminEnable")
    public QuarkAdminResult saveAdminEnable(@RequestParam(value = "id[]") Integer[] id) {
        QuarkAdminResult result = restProcessor(() -> {
            adminUserService.saveAdminEnable(id);
            return QuarkAdminResult.ok();
        });
        return result;
    }

}
