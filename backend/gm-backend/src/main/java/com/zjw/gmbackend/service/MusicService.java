package com.zjw.gmbackend.service;

import com.zjw.gmbackend.vo.MusicVO;

import java.util.List;

public interface MusicService {

    List<MusicVO> getRecommendList();

    MusicVO getMusicDetail(Long musicId);

    List<MusicVO> listByTag(String tag);
}
