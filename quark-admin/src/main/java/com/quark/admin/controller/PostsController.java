package com.quark.admin.controller;

import com.quark.admin.service.PostsService;
import com.quark.admin.service.UserService;
import com.quark.common.base.BaseController;
import com.quark.common.dto.PageResult;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.Posts;
import com.quark.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author LHR
 * Create By 2017/9/3
 */
@RequestMapping("/posts")
@RestController
public class PostsController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostsService postsService;

    @GetMapping
    public PageResult getAll(Posts posts, Integer uid, String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length) {
        int pageNo = start / length;
        if (uid != null) {
            User user = userService.findOne(uid);
            posts.setUser(user);
        }
        Page<Posts> page = postsService.findByPage(posts, pageNo, length);
        PageResult<List<Posts>> result = new PageResult<>(
                draw,
                page.getTotalElements(),
                page.getTotalElements(),
                page.getContent());
        return result;
    }

    @PostMapping("/saveTop")
    public QuarkResult saveTop(@RequestParam(value = "id[]") Integer[] id) {
        QuarkResult result = restProcessor(() -> {
            postsService.changeTop(id);
            return QuarkResult.ok();
        });
        return result;
    }

    @PostMapping("/saveGood")
    public QuarkResult saveGood(@RequestParam(value = "id[]") Integer[] id) {
        QuarkResult result = restProcessor(() -> {
            postsService.changeGood(id);
            return QuarkResult.ok();
        });
        return result;
    }

    @PostMapping("/delete")
    public QuarkResult deletePosts(@RequestParam(value = "id[]") Posts[] id){
        QuarkResult result = restProcessor(() -> {
            postsService.deleteInBatch(Arrays.asList(id));
            return QuarkResult.ok();
        });
        return result;
    }
}
