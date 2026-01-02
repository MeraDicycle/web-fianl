package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.service.MusicService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
public class MusicController {

    @Resource
    private MusicService musicService;

    @GetMapping("/recommend")
    public Result recommend() {
        return Result.success(musicService.getRecommendList());
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        return Result.success(musicService.getMusicDetail(id));
    }

    @GetMapping("/tag")
    public Result listByTag(@RequestParam String tag) {
        return Result.success(musicService.listByTag(tag));
    }

    /**
     * 修改歌曲信息（后台）
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id,
                         @RequestBody Music music) {

        music.setId(id); // 防止前端乱传 id
        musicService.updateMusic(music);
        return Result.success();
    }

    /**
     * 删除歌曲（后台）
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        musicService.deleteMusic(id);
        return Result.success();
    }

    /** 分页查询歌曲 */
    @GetMapping("/list")
    public Result list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return Result.success(musicService.pageList(page, size));
    }

    /** 新增歌曲 */
    @PostMapping
    public Result create(@RequestBody Music music) {
        musicService.createMusic(music);
        return Result.success();
    }
}
