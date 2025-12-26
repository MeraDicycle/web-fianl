package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.Playlist;

import java.util.List;

public interface FavoriteService {
    void toggleMusicFavorite(Long userId, Long musicId);
    List<Music> listFavoriteMusic(Long userId);

    List<Playlist> listFavoritePlaylist(Long userId);

    void togglePlaylistFavorite(Long userId, Long playlistId);
}

