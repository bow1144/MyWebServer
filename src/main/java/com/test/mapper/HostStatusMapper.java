package com.test.mapper;

import com.test.domain.HostStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HostStatusMapper {

    // 根据主机名查询状态
    HostStatus findByHostname(String hostname);

    // 查询近期所有状态
    List<HostStatus> getAllStatus();
}
