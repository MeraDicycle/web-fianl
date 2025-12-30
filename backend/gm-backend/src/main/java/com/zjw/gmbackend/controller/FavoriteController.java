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

//    喜欢 <---> 不喜欢
    @PostMapping("/music/{musicId}")
    public Result toggleMusic(@PathVariable Long musicId) {
        Long userId = UserContext.getUserId(); // 从 JWT 里拿
//        Long userId = (long)101;
        favoriteService.toggleMusicFavorite(userId, musicId);
        return Result.success();
    }

    @PostMapping("/playlist/{playlistId}")
    public Result togglePlaylist(@PathVariable Long playlistId) {
//        Long userId = 101L;
        Long userId = UserContext.getUserId();
        favoriteService.togglePlaylistFavorite(userId, playlistId);
        return Result.success();
    }

    @GetMapping("/music")
    public Result listMyFavoriteMusic() {
        Long userId = UserContext.getUserId();
//        Long userId = 101L;
        return Result.success(favoriteService.listFavoriteMusic(userId));
    }

    @GetMapping("/playlist")
    public Result listMyFavoritePlaylist() {
        Long userId = UserContext.getUserId();
//        Long userId = 101L;
        return Result.success(favoriteService.listFavoritePlaylist(userId));
    }
}

