package com.quark.admin.service.impl;

import com.quark.admin.service.UserService;
import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.UserDao;
import com.quark.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author LHR
 * Create By 2017/8/25
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao,User> implements UserService {

    @Override
    public Page<User> findByPage(User user, int pageNo, int length) {
        PageRequest pageable = new PageRequest(pageNo, length);

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> $id = root.get("id");
                Path<String> $username = root.get("username");
                Path<String> $email = root.get("email");
                Path<Integer> $enable = root.get("enable");

                ArrayList<Predicate> list = new ArrayList<>();
                if (user.getId() != null) list.add(criteriaBuilder.equal($id, user.getId()));
                if (user.getEnable() != null) list.add(criteriaBuilder.equal($enable, user.getEnable()));
                if (user.getUsername() != null)
                    list.add(criteriaBuilder.like($username, "%" + user.getUsername() + "%"));
                if (user.getEmail()!=null)
                    list.add(criteriaBuilder.like($email, "%" + user.getEmail() + "%"));

                Predicate predicate = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
                return predicate;
            }
        };
        Page<User> page = repository.findAll(specification, pageable);

        return page;
    }

    @Override
    public void saveUserEnable(Integer[] id) {
        List<User> all = findAll(Arrays.asList(id));
        for (User user :all) {
            if (user.getEnable() == 1) {
                user.setEnable(0);
            } else {
                user.setEnable(1);
            }
        }
        save(all);
    }
}
