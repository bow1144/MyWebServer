package com.test.mapper;

import com.test.domain.HostDetails;
import com.test.domain.HostStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HostDetailsMapper {

    // 向数据库HostDetails插入信息
    void insertHostDetails(HostDetails hostDetails);

    List<HostDetails> getAllDetails();


}
