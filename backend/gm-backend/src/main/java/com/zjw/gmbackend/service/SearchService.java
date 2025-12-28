package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Music;

import java.util.List;

public interface SearchService {
    List<Music> searchMusic(String keyword);
}

