CREATE DATABASE IF NOT EXISTS green_music
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE green_music;


CREATE TABLE `user` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    `nickname` VARCHAR(50) NOT NULL,
    `avatar_url` VARCHAR(255),
    `role` TINYINT NOT NULL DEFAULT 0 COMMENT '0=普通用户, 1=管理员',
    `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `music` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(100) NOT NULL,
    `artist` VARCHAR(100) NOT NULL,
    `album` VARCHAR(100),
    `cover_url` VARCHAR(255),
    `file_url` VARCHAR(255) NOT NULL,
    `lyric` TEXT,
    `tags` VARCHAR(200) COMMENT 'JSON 或逗号分隔标签',
    `duration_sec` INT NOT NULL,
    `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `playlist` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `cover_url` VARCHAR(255),
    `description` VARCHAR(255),
    `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `playlist_music` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `playlist_id` BIGINT NOT NULL,
    `music_id` BIGINT NOT NULL,
    `sort_order` INT DEFAULT 0,
    UNIQUE KEY uk_playlist_music (`playlist_id`, `music_id`),
    INDEX idx_playlist_id (`playlist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `favorite` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `target_type` TINYINT NOT NULL COMMENT '1=歌曲, 2=歌单',
    `target_id` BIGINT NOT NULL,
    `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_favorite (`user_id`, `target_type`, `target_id`),
    INDEX idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `history` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `music_id` BIGINT NOT NULL,
    `play_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_user_time (`user_id`, `play_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `comment` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `music_id` BIGINT NOT NULL,
    `content` VARCHAR(500) NOT NULL,
    `like_count` INT DEFAULT 0,
    `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_music_id (`music_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `comment_like` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `comment_id` BIGINT NOT NULL,
    `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_comment (`user_id`, `comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (username, password, nickname, role)
VALUES ('admin', '123456', '管理员', 1);

INSERT INTO `user` (id, username, password, nickname, avatar_url, role)
VALUES
(101, 'alice', '123456', 'Alice', 'https://picsum.photos/100?1', 0),
(102, 'bob',   '123456', 'Bob',   'https://picsum.photos/100?2', 0);

INSERT INTO `music`
(id, title, artist, album, cover_url, file_url, lyric, tags, duration_sec)
VALUES
(1, 'Green Breeze', 'No Copyright Music', 'Demo Album',
 'https://picsum.photos/300?music1',
 '/music/green_breeze.mp3',
 'Instrumental music only.',
 '轻音乐,纯音乐',
 210),

(2, 'Night Walk', 'No Copyright Music', 'Demo Album',
 'https://picsum.photos/300?music2',
 '/music/night_walk.mp3',
 'Relax and chill.',
 '轻音乐,放松',
 185),

(3, 'City Lights', 'No Copyright Music', 'Urban Demo',
 'https://picsum.photos/300?music3',
 '/music/city_lights.mp3',
 'Urban style background music.',
 '流行,背景音乐',
 240),

(4, 'Morning Sun', 'No Copyright Music', 'Daily Demo',
 'https://picsum.photos/300?music4',
 '/music/morning_sun.mp3',
 'Good morning!',
 '轻音乐,治愈',
 195),

(5, 'Coding Time', 'No Copyright Music', 'Developer Demo',
 'https://picsum.photos/300?music5',
 '/music/coding_time.mp3',
 'Focus and coding.',
 '纯音乐,学习',
 300);

INSERT INTO `playlist`
(id, user_id, name, cover_url, description)
VALUES
(1, 1, '学习专用歌单',
 'https://picsum.photos/300?playlist1',
 '适合学习和专注的时候听'),

(2, 2, '夜晚放松',
 'https://picsum.photos/300?playlist2',
 '夜晚散步或放松用');

INSERT INTO `playlist_music`
(playlist_id, music_id, sort_order)
VALUES
(1, 5, 1),
(1, 1, 2),
(1, 4, 3),

(2, 2, 1),
(2, 3, 2);

INSERT INTO `favorite`
(user_id, target_type, target_id)
VALUES
(1, 1, 1),  -- 喜欢 Green Breeze
(1, 1, 5),  -- 喜欢 Coding Time
(1, 2, 2);  -- 收藏 Bob 的歌单

INSERT INTO `favorite`
(user_id, target_type, target_id)
VALUES
(101, 1, 1),  -- 喜欢 Green Breeze
(101, 1, 5),  -- 喜欢 Coding Time
(101, 2, 2);  -- 收藏 Bob 的歌单

INSERT INTO `favorite`
(user_id, target_type, target_id)
VALUES
(102, 1, 2),
(102, 1, 3);

INSERT INTO `history`
(user_id, music_id, play_time)
VALUES
(101, 5, NOW()),
(101, 1, NOW() - INTERVAL 10 MINUTE),
(101, 4, NOW() - INTERVAL 30 MINUTE),

(102, 2, NOW()),
(102, 3, NOW() - INTERVAL 5 MINUTE);



