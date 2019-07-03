package com.zhongruan.controller;

import com.github.pagehelper.PageInfo;
import com.zhongruan.bean.UserInfo;
import com.zhongruan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        List<UserInfo> infos = userService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(infos);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }



    /*
    @RequestMapping("login.do")
    public ModelAndView dologin(UserInfo userInfo) {
        Boolean flag = userService.doLogin(userInfo);
        ModelAndView modelAndView = new ModelAndView();
        if (flag) {
            modelAndView.setViewName("main");
        } else {
            modelAndView.setViewName("../failer");
        }
        return modelAndView;
    }
*/
    @RequestMapping("save.do")
    public String add(UserInfo userInfo) {
        userService.save(userInfo);
        return ("redirect:findAll.do");
    }

    @RequestMapping("delete.do")
    public String delUser(long id) {
        userService.delete(id);
        return "redirect:/user/findAll.do";
    }

/*
    @RequestMapping("toupdate.do")
    public String toUodate(){
//    public String toUpdateUser(Model model, long id) {
//        model.addAttribute("userInfo", userService.queryById(id));
        return "user-update";
    }
/*
    @RequestMapping("update.do")
    public String updateUser(Model model, UserInfo userInfo) {
        userService.update(userInfo);
        userInfo = userService.queryById(userInfo.getId());
        model.addAttribute("userInfo", userInfo);
        return "redirect:/user/findAll.do";
    }
*/

    @RequestMapping("update.do")
    public String findAll(UserInfo userInfo,@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        List<UserInfo> infos = userService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(infos);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        userService.update(userInfo);
        return "redirect:/user/findAll.do";
    }
}