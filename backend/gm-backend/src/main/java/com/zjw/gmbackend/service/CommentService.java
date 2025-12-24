package com.zjw.gmbackend.service;

import com.zjw.gmbackend.pojo.Comment;

public interface CommentService {
    void addComment(Comment dto);

    Object listByMusicId(Long musicId);
}
