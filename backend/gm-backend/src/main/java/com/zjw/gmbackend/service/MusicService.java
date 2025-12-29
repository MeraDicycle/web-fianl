package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Music;

import java.util.List;

public interface MusicService {

    List<Music> getRecommendList();

    Music getMusicDetail(Long musicId);

    List<Music> listByTag(String tag);

    public List<Music> getRankList();
}
