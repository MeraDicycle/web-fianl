package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.User;

import java.util.List;

public interface UserService {

    List<Music> listHistory(Long userId);
    User findByUsername(String username);
    void register(User user);
    void updateProfile(Long userId, String nickname, String avatarUrl);
    User getById(Long userId);
}

