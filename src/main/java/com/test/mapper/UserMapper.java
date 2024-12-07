package com.test.mapper;

import com.test.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

import java.util.List;

@Mapper
public interface UserMapper {

    // 查询所有用户
    // @Select("SELECT * FROM user")
    List<User> getAllUsers();

    User getUserById(@Param("id") Long id);

    void updateUser(User user);

    void addUser(User user);

    void deleteUserById(Long id);

    List<LocalDateTime> getDetailById(Long id);

    List<LocalDateTime> getMonthlyDetailById(Long id);
}
