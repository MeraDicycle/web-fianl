package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {

    int insertFavorite(@Param("userId") Long userId,
                       @Param("musicId") Long musicId);

    int deleteFavorite(@Param("userId") Long userId,
                       @Param("musicId") Long musicId);

    int exists(@Param("userId") Long userId,
               @Param("musicId") Long musicId);

    List<Music> selectFavoriteMusicList(Long userId);
}

