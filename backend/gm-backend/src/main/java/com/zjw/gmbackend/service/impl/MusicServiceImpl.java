package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.FavoriteMapper;
import com.zjw.gmbackend.mapper.MusicMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.service.MusicService;
import com.zjw.gmbackend.util.UserContext;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        Long userId = 101L;
        Long userId = UserContext.getUserId();

        Integer count = favoriteMapper.exists(userId, 1, musicId);
        music.setLiked(count > 0);

        return music;
    }

    @Override
    public List<Music> listByTag(String tag) {
        return musicMapper.selectByTag(tag);
    }

    @Override
    public List<Music> getRankList() {
        return musicMapper.selectRankList();
    }

    @Override
    public void updateMusic(Music music) {
        musicMapper.update(music);
    }

    @Override
    public void deleteMusic(Long id) {
        musicMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> pageList(Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<Music> list = musicMapper.selectPage(offset, size);
        int total = musicMapper.count();

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        return result;
    }

    @Override
    public void createMusic(Music music) {
        musicMapper.insert(music);
    }
}
