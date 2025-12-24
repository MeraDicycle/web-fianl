package com.zjw.gmbackend.dto;

public class PlaylistDTO {
    private Long userId;      // 先从前端传, 后期dto.getUserId() -> JwtUtil.getUserId()



    private String name;
    private String description;
}
