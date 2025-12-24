package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.PlaylistMapper;
import com.zjw.gmbackend.pojo.Music;
import com.zjw.gmbackend.pojo.Playlist;
import com.zjw.gmbackend.service.PlaylistService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Resource
    private PlaylistMapper playlistMapper;

    @Override
    public Object getDetail(Long id) {
        // 1. 查询歌单基本信息
        Playlist playlist = playlistMapper.selectById(id);
        if (playlist == null) {
            return null;
        }

        // 2. 查询歌单中的歌曲列表
        List<Music> musicList = playlistMapper.selectMusicByPlaylistId(id);

        // 3. 组装返回结构（简单 Map，不搞 VO）
        Map<String, Object> result = new HashMap<>();
        result.put("playlist", playlist);
        result.put("musicList", musicList);

        return result;
    }

    @Override
    public void createPlaylist(Playlist playlist) {
        // TODO：等你做 JWT 之后，这里从 UserContext 拿 userId
        // Long userId = UserContext.getUserId();
        // playlist.setUserId(userId);

        playlist.setCreatedTime(LocalDateTime.now());
        playlistMapper.insert(playlist);
    }
}
