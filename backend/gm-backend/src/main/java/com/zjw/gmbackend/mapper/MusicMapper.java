package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicMapper {

    public List<Music> selectRecommendList();

    public Music selectMusicDetail(@Param("id") Long musicId);

    public List<Music> selectByTag(@Param("tag") String tag);

    List<Music> searchByKeyword(@Param("keyword") String keyword);

    List<Music> selectHistoryMusic(@Param("userId") Long userId);

}
