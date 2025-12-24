package com.zjw.gmbackend.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Resource
    private PlaylistService playlistService;

    @PostMapping("/create")
    public Result<Void> create(@RequestBody PlaylistDTO dto) {
        playlistService.createPlaylist(dto);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<PlaylistVO> detail(@PathVariable Long id) {
        return Result.success(playlistService.getDetail(id));
    }
}

