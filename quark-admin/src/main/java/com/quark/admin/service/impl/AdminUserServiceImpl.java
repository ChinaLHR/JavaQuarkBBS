package com.quark.admin.service.impl;

import com.quark.admin.service.AdminUserService;
import com.quark.admin.service.RoleService;
import com.quark.admin.utils.PasswordHelper;
import com.quark.common.Base.BaseServiceImpl;
import com.quark.common.dao.AdminUserDao;
import com.quark.common.entity.AdminUser;
import com.quark.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by lhr on 17-8-1.
 */
@Service
@Transactional
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUserDao, AdminUser> implements AdminUserService {

    @Autowired
    private RoleService roleService;

    @Override
    public AdminUser findByUserName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Page<AdminUser> findByPage(AdminUser adminUser, int pageNo, int length) {
        PageRequest pageable = new PageRequest(pageNo, length);

        Specification<AdminUser> specification = new Specification<AdminUser>() {

            @Override
            public Predicate toPredicate(Root<AdminUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> $id = root.get("id");
                Path<String> $username = root.get("username");
                Path<Integer> $enable = root.get("enable");

                ArrayList<Predicate> list = new ArrayList<>();
                if (adminUser.getId() != null) list.add(criteriaBuilder.equal($id, adminUser.getId()));
                if (adminUser.getEnable() != null) list.add(criteriaBuilder.equal($enable, adminUser.getEnable()));
                if (adminUser.getUsername() != null)
                    list.add(criteriaBuilder.like($username, "%" + adminUser.getUsername() + "%"));

                Predicate predicate = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
                return predicate;
            }


        };

        Page<AdminUser> page = repository.findAll(specification, pageable);

        return page;
    }

    @Override
    public void saveAdmin(AdminUser entity) {
            entity.setEnable(1);
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(entity);
            save(entity);
    }

    @Override
    public void saveAdminRoles(Integer uid, Integer[] roles) {
        AdminUser adminUser = findOne(uid);
        Set<Role> roleSet = new HashSet<>();
        Stream.of(roles).forEach(r -> {
            roleSet.add(roleService.findOne(r));
        });
        adminUser.setRoles(roleSet);
        save(adminUser);
    }

    @Override
    public void saveAdminEnable(Integer[] ids) {
        Stream.of(ids).forEach(i->{
            AdminUser adminUser = findOne(i);
            if (adminUser.getEnable() == 1) {
                adminUser.setEnable(0);
            } else {
                adminUser.setEnable(1);
            }
            save(adminUser);
        });
    }
}
