package com.quark.admin.service.impl;

import com.quark.admin.service.PostsService;
import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.PostsDao;
import com.quark.common.entity.Posts;
import com.quark.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author LHR
 * Create By 2017/9/3
 */
@Service
public class PostsServiceImpl extends BaseServiceImpl<PostsDao,Posts> implements PostsService {


    @Override
    public Page<Posts> findByPage(Posts posts, int pageNo, int length) {
        PageRequest pageable = new PageRequest(pageNo, length);
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = new Sort(order);

        Specification<Posts> specification = new Specification<Posts>() {

            @Override
            public Predicate toPredicate(Root<Posts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> $id = root.get("id");
                Path<String> $title = root.get("title");
                Path<User> $user = root.get("user");
                Path<Boolean> $top = root.get("top");
                Path<Boolean> $good = root.get("good");

                ArrayList<Predicate> list = new ArrayList<>();
                if (posts.getId()!=null) list.add(criteriaBuilder.equal($id,posts.getId()));
                if (posts.getTitle()!=null) list.add(criteriaBuilder.like($title,"%" + posts.getTitle() + "%"));
                if (posts.getUser()!=null) list.add(criteriaBuilder.equal($user,posts.getUser()));
                if (posts.getTop()==true) list.add(criteriaBuilder.equal($top,true));
                if (posts.getGood()==true) list.add(criteriaBuilder.equal($good,true));

                Predicate predicate = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));

                return predicate;
            }
        };
        Page<Posts> page = repository.findAll(specification, pageable);
        return page;
    }

    @Override
    public void changeTop(Integer[] ids) {
        List<Posts> all = findAll(Arrays.asList(ids));
        for (Posts p :all) {
            if (p.getTop()==false) p.setTop(true);
            else p.setTop(false);
        }
        save(all);
    }

    @Override
    public void changeGood(Integer[] ids) {
        List<Posts> all = findAll(Arrays.asList(ids));
        for (Posts p :all) {
            if (p.getGood()==false) p.setGood(true);
            else p.setGood(false);
        }
        save(all);
    }


}
