package com.test.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
    // 简单的账号密码验证方法（实际应用中应该查询数据库）
    public boolean validateCredentials(String username, String password) {
        // 假设我们有一个用户名"admin"和密码"1234"的硬编码验证
        return "admin".equals(username) && "1234".equals(password);
    }
}
