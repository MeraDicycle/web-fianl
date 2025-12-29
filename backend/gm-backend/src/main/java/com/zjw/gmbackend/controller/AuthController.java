package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.pojo.User;
import com.zjw.gmbackend.service.UserService;
import com.zjw.gmbackend.util.JwtUtil;
import com.zjw.gmbackend.util.PasswordUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        // 1. 查用户
        User user = userService.findByUsername(loginUser.getUsername());
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 2. 校验密码（当前阶段：明文）
        if (!PasswordUtil.matches(loginUser.getPassword(), user.getPassword())) {
            return Result.error("密码错误");
        }


        // 3. 生成 token
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());

        user.setPassword(null);

        // 4. 返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);

        return Result.success(data);
    }
}
