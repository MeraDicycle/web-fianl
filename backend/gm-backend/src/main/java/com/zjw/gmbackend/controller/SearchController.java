package com.zjw.gmbackend.controller;

import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.service.SearchService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchService searchService;

    @GetMapping
    public Result search(@RequestParam String keyword) {
        return Result.success(searchService.searchMusic(keyword));
    }
}

