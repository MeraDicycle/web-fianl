package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);

    int insert(User user);
}

