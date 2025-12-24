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
}

