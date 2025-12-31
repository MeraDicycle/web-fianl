package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.pojo.User;
import com.zjw.gmbackend.service.UserService;
import com.zjw.gmbackend.util.UserContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Long MOCK_USER_ID = 101L;

    @Resource
    private UserService userService;

    // 播放历史
    @GetMapping("/history")
    public Result history() {
        Long userId = UserContext.getUserId();
//        return Result.success(userService.listHistory(MOCK_USER_ID));
        return Result.success(userService.listHistory(userId));
    }

    @PutMapping("/profile")
    public Result updateProfile(@RequestBody User req) {

        Long userId = UserContext.getUserId();

        userService.updateProfile(
                userId,
                req.getNickname(),
                req.getAvatarUrl()
        );

        return Result.success();
    }

    /**
     * 获取当前用户信息（前端初始化用）
     */
    @GetMapping("/me")
    public Result me() {
        Long userId = UserContext.getUserId();
        return Result.success(userService.getById(userId));
    }


}

