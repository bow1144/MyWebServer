package com.test.service;

import com.test.domain.HostStatus;
import com.test.repository.HostStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorService {

    @Autowired
    private HostStatusRepository hostStatusRepository;

    // TODO:已经设计好HostStatue类，接下来读取数据库

    // 保存主机状态
    public void updateHostStatus(HostStatus hostStatus) {
        hostStatusRepository.save(hostStatus);  // 保存到数据库
    }

    // 根据主机名查询主机状态
    public HostStatus getHostStatus(String hostname) {
        return hostStatusRepository.findByHostname(hostname);  // 从数据库中查询
    }
}
