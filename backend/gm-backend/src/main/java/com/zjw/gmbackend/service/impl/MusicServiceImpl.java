package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.FavoriteMapper;
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
    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public List<Music> getRecommendList() {
        return musicMapper.selectRecommendList();
    }

    @Override
    public Music getMusicDetail(Long musicId) {
// 1. 查询歌曲
        Music music = musicMapper.selectMusicDetail(musicId);
        if (music == null) {
            return null;
        }

        // 2. 查询是否已收藏
        Long userId = 101L;
//        Long userId = UserContext.getUserId();

        Integer count = favoriteMapper.exists(userId, 1, musicId);
        music.setLiked(count > 0);

        return music;
    }

    @Override
    public List<Music> listByTag(String tag) {
        return musicMapper.selectByTag(tag);
    }
}
