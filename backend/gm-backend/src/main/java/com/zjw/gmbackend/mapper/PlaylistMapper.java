package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.Playlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaylistMapper {
    Object getDetail(Long id);

    void createPlaylist(Playlist playlist);

    Playlist selectById(@Param("id") Long id);

    List<Music> selectMusicByPlaylistId(@Param("playlistId") Long playlistId);

    int insert(Playlist playlist);
}
