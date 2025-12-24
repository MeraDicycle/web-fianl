package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Playlist;

public interface PlaylistService {
    Object getDetail(Long id);

    void createPlaylist(Playlist playlist);
}
