package com.zjw.gmbackend.mapper;

import com.zjw.gmbackend.pojo.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicMapper {
    public List<Music> selectRecommendList() {
        return new ArrayList<Music>();
    }

    public Music selectMusicDetail(Long musicId) {
        return new Music();
    }

    public List<Music> selectByTag(String tag) {
        return new ArrayList<>();
    }
}
