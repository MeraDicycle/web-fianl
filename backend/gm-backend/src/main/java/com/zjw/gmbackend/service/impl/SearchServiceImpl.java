package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.MusicMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.service.SearchService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private MusicMapper musicMapper;

    @Override
    public List<Music> searchMusic(String keyword) {
        return musicMapper.searchByKeyword(keyword);
    }
}
