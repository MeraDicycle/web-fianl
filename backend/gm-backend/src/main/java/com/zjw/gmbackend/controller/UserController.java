package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.service.UserService;
import com.zjw.gmbackend.util.UserContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

