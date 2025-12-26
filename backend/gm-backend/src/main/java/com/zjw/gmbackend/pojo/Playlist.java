package com.zjw.gmbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    private Long id;
    private Long userId;      // 先从前端传, 后期dto.getUserId() -> JwtUtil.getUserId()
    private String name;
    private String description;
    private LocalDateTime createdTime;
    private String coverUrl;

    //not database 字段
    private Boolean liked;
}

