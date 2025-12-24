package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Music;

import java.util.List;

public interface FavoriteService {
    void toggleMusicFavorite(Long userId, Long musicId);
    List<Music> listFavoriteMusic(Long userId);
}

