package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.MusicMapper;
import com.zjw.gmbackend.mapper.UserMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.User;
import com.zjw.gmbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MusicMapper musicMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Music> listHistory(Long userId) {
        return musicMapper.selectHistoryMusic(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    @Override
    public void register(User user) {

        // 1️⃣ 参数校验
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new RuntimeException("用户名或密码不能为空");
        }

        // 2️⃣ 用户名是否存在
        User exist = userMapper.findByUsername(user.getUsername());
        if (exist != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 3️⃣ 密码加密
        String encodedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPwd);

        // 4️⃣ 默认字段
        user.setRole(0); // 普通用户
        user.setAvatarUrl(null);
        user.setCreatedTime(LocalDateTime.now());

        // 5️⃣ 入库
        userMapper.insert(user);
    }
}

