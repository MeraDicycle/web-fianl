package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Music;

import java.util.List;
import java.util.Map;

public interface MusicService {

    List<Music> getRecommendList();

    Music getMusicDetail(Long musicId);

    List<Music> listByTag(String tag);

    public List<Music> getRankList();

    // ===== 后台管理 =====
    void updateMusic(Music music);

    void deleteMusic(Long id);

    Map<String, Object> pageList(Integer page, Integer size);

    void createMusic(Music music);
}
