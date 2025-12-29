package com.zjw.gmbackend.service.impl;

import com.zjw.gmbackend.mapper.FavoriteMapper;
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

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public Object getDetail(Long id) {
        // 1. 查询歌单基本信息
        Playlist playlist = playlistMapper.selectById(id);
        if (playlist == null) {
            return null;
        }

        // 2. 查询歌单中的歌曲列表
        List<Music> musicList = playlistMapper.selectMusicByPlaylistId(id);

        // 3. 查询是否已收藏（liked）
        Long userId = 101L;
//        Long userId = UserContext.getUserId();

        Integer count = favoriteMapper.exists(userId, 2, id);
        playlist.setLiked(count > 0);

        // 4. 组装返回结构
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

    @Override
    public void addMusicToPlaylist(Long playlistId, Long musicId) {
        Integer count = playlistMapper.existsMusic(playlistId, musicId);
        if (count > 0) {
            return; // 已存在，直接忽略（前端体验更好）
        }

        Integer maxOrder = playlistMapper.selectMaxSortOrder(playlistId);
        int sortOrder = maxOrder == null ? 1 : maxOrder + 1;

        playlistMapper.insertPlaylistMusic(playlistId, musicId, sortOrder);
    }

    @Override
    public void removeMusicFromPlaylist(Long playlistId, Long musicId) {
        playlistMapper.deletePlaylistMusic(playlistId, musicId);
    }

    @Override
    public Object list(String category, Integer page, Integer size) {
        int offset = (page - 1) * size;

        List<Playlist> list = playlistMapper.selectList(category, offset, size);
        Integer total = playlistMapper.count(category);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);

        return result;
    }

    @Override
    public List<Playlist> listByUserId(Long userId) {
        return playlistMapper.selectByUserId(userId);
    }
}

