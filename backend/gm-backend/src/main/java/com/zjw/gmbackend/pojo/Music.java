package com.zjw.gmbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    private Long id;
    private String title;
    private String artist;
    private String coverUrl;
    private Integer durationSec;
    private String fileUrl;
    private String lyric;
    private String tags;
    private Boolean liked;
}
