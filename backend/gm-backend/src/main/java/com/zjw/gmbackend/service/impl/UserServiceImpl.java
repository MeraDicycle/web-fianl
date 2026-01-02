package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.MusicMapper;
import com.zjw.gmbackend.mapper.UserMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.User;
import com.zjw.gmbackend.service.UserService;
import com.zjw.gmbackend.util.PasswordUtil;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void updateProfile(Long userId, String nickname, String avatarUrl) {

        User user = new User();
        user.setId(userId);
        user.setNickname(nickname);
        user.setAvatarUrl(avatarUrl);

        userMapper.updateProfile(user);
    }

    @Override
    public User getById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public void addHistory(Long userId, Long musicId) {

        // 1. 删除同一用户下的旧记录（避免重复）
        userMapper.deleteByUserAndMusic(userId, musicId);

        // 2. 插入新记录
        userMapper.insertHistory(userId, musicId);

        // 3. 只保留最近 20 条
        userMapper.deleteExceedLimit(userId, 20);
    }

    @Override
    public Map<String, Object> pageList(Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<User> list = userMapper.selectPage(offset, size);
        int total = userMapper.count();

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);
        return map;
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void resetPassword(Long userId) {
        // 1. 明文密码
        String rawPassword = "123456";

        // 2. BCrypt 加密
        String encodedPassword = PasswordUtil.encode(rawPassword);

        // 3. 更新数据库
        userMapper.updatePassword(userId, encodedPassword);
    }


    @Override
    public void changePassword(Long userId,
                               String oldPassword,
                               String newPassword) {

        // 1. 查用户
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 校验旧密码
        boolean match = PasswordUtil.matches(oldPassword, user.getPassword());
        if (!match) {
            throw new RuntimeException("原密码错误");
        }

        // 3. 新密码加密
        String encoded = PasswordUtil.encode(newPassword);

        // 4. 更新数据库
        userMapper.updatePassword(userId, encoded);
    }
}

