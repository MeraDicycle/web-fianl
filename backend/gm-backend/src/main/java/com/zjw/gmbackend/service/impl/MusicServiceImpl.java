package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.MusicMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.service.MusicService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicMapper musicMapper;

    @Override
    public List<Music> getRecommendList() {
        return musicMapper.selectRecommendList();
    }

    @Override
    public Music getMusicDetail(Long musicId) {
        return musicMapper.selectMusicDetail(musicId);
    }

    @Override
    public List<Music> listByTag(String tag) {
        return musicMapper.selectByTag(tag);
    }
}
