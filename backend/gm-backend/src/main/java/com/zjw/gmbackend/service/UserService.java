package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<Music> listHistory(Long userId);
    User findByUsername(String username);
    void register(User user);
    void updateProfile(Long userId, String nickname, String avatarUrl);
    User getById(Long userId);
    void addHistory(Long userId, Long musicId);

    Map<String, Object> pageList(Integer page, Integer size);

    void updateUser(User user);

    void deleteUser(Long id);

    void resetPassword(Long userId);

    void changePassword(Long userId, String oldPassword, String newPassword);
}

