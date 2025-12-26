package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.FavoriteMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.Playlist;
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
        int exists = favoriteMapper.exists(userId, 1, musicId);
        if (exists > 0) {
            favoriteMapper.delete(userId, 1, musicId);
        } else {
            favoriteMapper.insert(userId, 1, musicId);
        }
    }

    @Override
    public List<Music> listFavoriteMusic(Long userId) {
        return favoriteMapper.selectFavoriteMusicList(userId);
    }

    @Override
    public List<Playlist> listFavoritePlaylist(Long userId) {
        return favoriteMapper.selectFavoritePlaylistList(userId);
    }

    @Override
    public void togglePlaylistFavorite(Long userId, Long playlistId) {
        Integer count = favoriteMapper.exists(userId, 2, playlistId);
        if (count > 0) {
            favoriteMapper.delete(userId, 2, playlistId);
        } else {
            favoriteMapper.insert(userId, 2, playlistId);
        }
    }


}


