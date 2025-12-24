package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.FavoriteMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.service.FavoriteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public void toggleMusicFavorite(Long userId, Long musicId) {
        int exists = favoriteMapper.exists(userId, musicId);
        if (exists > 0) {
            favoriteMapper.deleteFavorite(userId, musicId);
        } else {
            favoriteMapper.insertFavorite(userId, musicId);
        }
    }

    @Override
    public List<Music> listFavoriteMusic(Long userId) {
        return favoriteMapper.selectFavoriteMusicList(userId);
    }
}


