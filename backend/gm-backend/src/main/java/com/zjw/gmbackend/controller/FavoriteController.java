package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.service.FavoriteService;
import com.zjw.gmbackend.util.UserContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Resource
    private FavoriteService favoriteService;

    @PostMapping("/music/{musicId}")
    public Result toggleMusic(@PathVariable Long musicId) {
        Long userId = UserContext.getUserId(); // 从 JWT 里拿
        favoriteService.toggleMusicFavorite(userId, musicId);
        return Result.success();
    }

    @GetMapping("/music")
    public Result listMyFavoriteMusic() {
        Long userId = UserContext.getUserId();
        return Result.success(favoriteService.listFavoriteMusic(userId));
    }
}

