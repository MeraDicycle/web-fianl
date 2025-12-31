package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.Playlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaylistMapper {

    Playlist selectById(Long id);

    List<Music> selectMusicByPlaylistId(Long playlistId);

    void insert(Playlist playlist);

    Integer existsMusic(@Param("playlistId") Long playlistId,
                        @Param("musicId") Long musicId);

    Integer selectMaxSortOrder(@Param("playlistId") Long playlistId);

    void insertPlaylistMusic(@Param("playlistId") Long playlistId,
                             @Param("musicId") Long musicId,
                             @Param("sortOrder") Integer sortOrder);

    void deletePlaylistMusic(@Param("playlistId") Long playlistId,
                             @Param("musicId") Long musicId);

    List<Playlist> selectList(@Param("category") String category,
                              @Param("offset") Integer offset,
                              @Param("size") Integer size);

    Integer count(@Param("category") String category);

    List<Playlist> selectByUserId(@Param("userId") Long userId);

    void deleteById(Long id);
    void deleteAllPlaylistMusic(Long playlistId);

    Long selectOwnerId(Long playlistId);



}

