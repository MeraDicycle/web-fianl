package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);

    int insert(User user);

    int updateProfile(User user);

    User selectById(Long id);

    void insertHistory(@Param("userId") Long userId,
                @Param("musicId") Long musicId);

    void deleteByUserAndMusic(@Param("userId") Long userId,
                              @Param("musicId") Long musicId);

    void deleteExceedLimit(@Param("userId") Long userId,
                           @Param("limit") Integer limit);

    List<User> selectPage(@Param("offset") int offset,
                          @Param("size") int size);

    int count();

    int update(User user);

    int deleteById(Long id);

    int updatePassword(@Param("id") Long id,
                       @Param("password") String password);
}

