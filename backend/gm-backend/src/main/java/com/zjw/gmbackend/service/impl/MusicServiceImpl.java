package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.MusicMapper;
import com.zjw.gmbackend.service.MusicService;
import com.zjw.gmbackend.vo.MusicVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicMapper musicMapper;

    @Override
    public List<MusicVO> getRecommendList() {
        return musicMapper.selectRecommendList();
    }

    @Override
    public MusicVO getMusicDetail(Long musicId) {
        return musicMapper.selectMusicDetail(musicId);
    }

    @Override
    public List<MusicVO> listByTag(String tag) {
        return musicMapper.selectByTag(tag);
    }
}
