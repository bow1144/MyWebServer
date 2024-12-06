package com.test.service;


import com.test.domain.User;
import com.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 获取所有用户
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

    public void updateUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("用户 ID 不能为空");
        }
        userMapper.updateUser(user);
    }

    public void addUser(User user) {
        if (user.getName() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("用户名和邮箱不能为空");
        }
        userMapper.addUser(user); // 调用 MyBatis 插入方法
    }
}