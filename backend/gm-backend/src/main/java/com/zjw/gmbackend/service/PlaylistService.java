package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Playlist;

public interface PlaylistService {
    Object getDetail(Long id);

    void createPlaylist(Playlist playlist);

    void addMusicToPlaylist(Long playlistId, Long musicId);

    void removeMusicFromPlaylist(Long playlistId, Long musicId);

    Object list(String category, Integer page, Integer size);
}
