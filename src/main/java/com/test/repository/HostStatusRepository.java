package com.test.repository;

import com.test.domain.HostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostStatusRepository extends JpaRepository<HostStatus, String> {
    HostStatus findByHostname(String hostname);  // 根据主机名查询状态
}
