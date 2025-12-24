package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.service.MusicService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
public class MusicController {

    @Resource
    private MusicService musicService;

    @GetMapping("/recommend")
    public Result<List<MusicVO>> recommend() {
        return Result.success(musicService.getRecommendList());
    }

    @GetMapping("/{id}")
    public Result<MusicVO> detail(@PathVariable Long id) {
        return Result.success(musicService.getMusicDetail(id));
    }

    @GetMapping("/tag")
    public Result<List<MusicVO>> listByTag(@RequestParam String tag) {
        return Result.success(musicService.listByTag(tag));
    }
}
