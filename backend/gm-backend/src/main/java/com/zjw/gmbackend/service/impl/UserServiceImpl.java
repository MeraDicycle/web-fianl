package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.MusicMapper;
import com.zjw.gmbackend.mapper.UserMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.User;
import com.zjw.gmbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MusicMapper musicMapper;

    @Override
    public List<Music> listHistory(Long userId) {
        return musicMapper.selectHistoryMusic(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}

