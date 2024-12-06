package com.test.controller;

import com.test.domain.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 获取所有用户
    @GetMapping()
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();  // 获取用户数据
        System.out.println("Users data: " + users);
        model.addAttribute("user", users);  // 将用户数据传递给模板
        return "user";  // 返回视图名 "user"（指向 user.html 模板）
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam("id") Long userId, Model model) {
        // 根据用户ID获取用户信息（从数据库或服务中查询）
        System.out.println("EditUser endpoint hit with ID: " + userId);
        User user = userService.getUserById(userId);
        System.out.println("User data: " + user);
        // 将用户信息添加到模型中
        model.addAttribute("user", user);
        // 返回编辑页面
        return "editUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user) {
        // 调用服务层更新用户信息
        userService.updateUser(user);
        // 重定向到用户列表页面
        return "redirect:/users";
    }

    @GetMapping("/addUser")
    public String addUserPage(Model model) {
        // 创建一个空的 User 对象用于表单绑定
        model.addAttribute("user", new User());
        return "addUser"; // 显示新增用户页面 (addUser.html)
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        // 打印接收到的用户信息，调试用
        System.out.println("Received User Data: " + user);

        // TODO: 调用服务层保存用户
        userService.addUser(user);

        // 重定向到用户列表页面
        return "redirect:/users";
    }
}
