package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.pojo.ChangePasswordDTO;
import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.pojo.User;
import com.zjw.gmbackend.service.UserService;
import com.zjw.gmbackend.util.UserContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

//    private static final Long MOCK_USER_ID = 101L;

    @Resource
    private UserService userService;

    // 播放历史
    @GetMapping("/history")
    public Result history() {
        Long userId = UserContext.getUserId();
//        return Result.success(userService.listHistory(MOCK_USER_ID));
        return Result.success(userService.listHistory(userId));
    }

    /** 记录播放历史 */
    @PostMapping("/history/{musicId}")
    public Result addHistory(@PathVariable Long musicId) {
        Long userId = UserContext.getUserId();
        userService.addHistory(userId, musicId);
        return Result.success();
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

    /** 分页查询用户 */
    @GetMapping("/list")
    public Result list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return Result.success(userService.pageList(page, size));
    }

    /** 修改用户（不改密码） */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id,
                         @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return Result.success();
    }

    /** 删除用户 */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success();
    }

    /**
     * 重置用户密码（后台）
     * 重置为：123456
     */
    @PostMapping("/{id}/reset-password")
    public Result resetPassword(@PathVariable Long id) {
        userService.resetPassword(id);
        return Result.success();
    }

    /**
     * 用户修改密码
     */
    @PostMapping("/change-password")
    public Result changePassword(@RequestBody ChangePasswordDTO dto) {

        Long userId = UserContext.getUserId(); // 从 JWT 中取
        userService.changePassword(userId, dto.getOldPassword(), dto.getNewPassword());
        return Result.success();
    }

}

