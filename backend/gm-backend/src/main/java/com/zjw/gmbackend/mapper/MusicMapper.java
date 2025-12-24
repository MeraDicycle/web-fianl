package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicMapper {
    public List<Music> selectRecommendList();

    public Music selectMusicDetail(Long musicId);

    public List<Music> selectByTag(String tag);
}
