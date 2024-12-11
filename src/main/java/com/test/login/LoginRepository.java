package com.test.login;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<ACT_PWD, String> {
    Optional<ACT_PWD> findByAccount(String account);  // 修改为正确的字段名
}
