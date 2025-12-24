package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/add")
    public Result<Void> add(@RequestBody CommentDTO dto) {
        commentService.addComment(dto);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<CommentVO>> list(@RequestParam Long musicId) {
        return Result.success(commentService.listByMusicId(musicId));
    }
}

