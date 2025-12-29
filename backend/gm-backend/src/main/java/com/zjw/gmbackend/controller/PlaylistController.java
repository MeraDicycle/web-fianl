package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.pojo.Playlist;
import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.service.PlaylistService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Resource
    private PlaylistService playlistService;

    @PostMapping("/create")
    public Result create(@RequestBody Playlist playlist) {
        playlistService.createPlaylist(playlist);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        return Result.success(playlistService.getDetail(id));
    }

    @PostMapping("/{playlistId}/music/{musicId}")
    public Result addMusic(@PathVariable Long playlistId,
                           @PathVariable Long musicId) {
        playlistService.addMusicToPlaylist(playlistId, musicId);
        return Result.success();
    }

    @DeleteMapping("/{playlistId}/music/{musicId}")
    public Result removeMusic(@PathVariable Long playlistId,
                              @PathVariable Long musicId) {
        playlistService.removeMusicFromPlaylist(playlistId, musicId);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(@RequestParam(required = false) String category,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(playlistService.list(category, page, size));
    }

    @GetMapping("/my")
    public Result listMyPlaylist() {
        // Long userId = UserContext.getUserId();
        Long userId = 101L;
        return Result.success(playlistService.listByUserId(userId));
    }



}

