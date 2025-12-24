package com.zjw.gmbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    private Long userId;      // 先从前端传, 后期dto.getUserId() -> JwtUtil.getUserId()
    private String name;
    private String description;
}

