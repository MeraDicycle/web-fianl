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
    private Long userId;
    private String name;
    private String description;
    private LocalDateTime createdTime;
    private String coverUrl;
    private String category;

    //not database 字段
    private Boolean liked;
}

