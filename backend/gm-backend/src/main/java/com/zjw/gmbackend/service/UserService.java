package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Music;

import java.util.List;

public interface UserService {

    List<Music> listHistory(Long userId);
}

