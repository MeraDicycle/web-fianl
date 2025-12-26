package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.Playlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {

//    int insertFavorite(@Param("userId") Long userId,
//                       @Param("musicId") Long musicId);
//
//    int deleteFavorite(@Param("userId") Long userId,
//                       @Param("musicId") Long musicId);


    Integer exists(@Param("userId") Long userId,
                   @Param("targetType") Integer targetType,
                   @Param("targetId") Long targetId);

    List<Music> selectFavoriteMusicList(Long userId);
    List<Playlist> selectFavoritePlaylistList(@Param("userId") Long userId);

    int insert(@Param("userId") Long userId,
               @Param("targetType") Integer targetType,
               @Param("targetId") Long targetId);

    int delete(@Param("userId") Long userId,
               @Param("targetType") Integer targetType,
               @Param("targetId") Long targetId);

}

