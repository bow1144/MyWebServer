package com.test.controller;

import com.test.login.ACT_PWD;
import com.test.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // 返回登录页面的视图
    }

    @PostMapping("/login")
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        Model model) {
        // 查询数据库，查找该账号的用户
        Optional<ACT_PWD> userOptional = loginRepository.findByAccount(account);

        // 如果找到用户
        if (userOptional.isPresent()) {
            ACT_PWD user = userOptional.get();
            // 检查密码是否匹配
            if (user.getPassword().equals(password)) {
                // 登录成功，跳转到用户列表页面
                return "redirect:/users";
            } else {
                // 密码错误，返回登录页面并提示错误
                model.addAttribute("error", "用户名或密码错误");
                return "login";
            }
        } else {
            // 如果没有找到用户，返回登录页面并提示错误
            model.addAttribute("error", "用户名不存在");
            return "login";
        }
    }
}
