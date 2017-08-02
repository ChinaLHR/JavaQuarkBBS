package com.quark.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lhr on 17-8-2.
 */
@Controller
public class PageController {

    /**
     * 登录界面
     * @return
     */
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }




}
