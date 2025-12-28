web-fianl
├── src/main/java
│   └── com
│       └── zjw
│           └── gmbackend
│               ├── GmBackendApplication.java
│               │
│               ├── config/
│               │   ├── WebConfig.java
│               │   ├── CorsConfig.java
│               │   ├── SwaggerConfig.java
│               │   └── JwtConfig.java
│               │
│               ├── controller/
│               │   ├── AuthController.java
│               │   ├── UserController.java
│               │   ├── MusicController.java	ok
│               │   ├── PlaylistController.java	ok
│               │   ├── //CommentController.java	不用做
│               │   ├── FavoriteController.java	ok
│               │   └── SearchController.java
│               │
│               ├── service/
│               │   ├── UserService.java
│               │   ├── MusicService.java
│               │   ├── PlaylistService.java
│               │   ├── CommentService.java
│               │   ├── FavoriteService.java
│               │   └── HistoryService.java
│               │
│               ├── service/impl/
│               │   ├── UserServiceImpl.java
│               │   ├── MusicServiceImpl.java
│               │   ├── PlaylistServiceImpl.java
│               │   ├── CommentServiceImpl.java
│               │   ├── FavoriteServiceImpl.java
│               │   └── HistoryServiceImpl.java
│               │
│               ├── mapper/
│               │   ├── UserMapper.java
│               │   ├── MusicMapper.java
│               │   ├── PlaylistMapper.java
│               │   ├── CommentMapper.java
│               │   ├── FavoriteMapper.java
│               │   └── HistoryMapper.java
│               │
│               ├── entity/            # 数据表实体
│               │   ├── User.java
│               │   ├── Music.java
│               │   ├── Playlist.java
│               │   ├── Comment.java
│               │   ├── Favorite.java
│               │   └── History.java
│               │
│               ├── dto/               # 接收前端参数（推荐）
│               │   ├── LoginDTO.java
│               │   ├── RegisterDTO.java
│               │   ├── CommentDTO.java
│               │   └── PlaylistDTO.java
│               │
│               ├── vo/                # 返回前端数据（加分）
│               │   ├── UserVO.java
│               │   ├── MusicVO.java
│               │   ├── PlaylistVO.java
│               │   └── CommentVO.java
│               │
│               ├── common/            # 通用封装
│               │   ├── Result.java
│               │   ├── ResultCode.java
│               │   └── PageResult.java
│               │
│               ├── exception/         # 全局异常
│               │   ├── BizException.java
│               │   └── GlobalExceptionHandler.java
│               │
│               └── util/              # 工具类
│                   ├── JwtUtil.java
│                   ├── PasswordUtil.java
│                   └── DateUtil.java
│
└── src/main/resources
    ├── mapper/                        # MyBatis XML
    │   ├── UserMapper.xml
    │   ├── MusicMapper.xml
    │   └── ...
    ├── application.yml
    └── application-dev.yml



1️⃣ Music（歌曲模块）        ← 最核心、零依赖
2️⃣ Playlist（歌单模块）     ← 轻度依赖 userId（可写死）
3️⃣ Comment（评论模块）      ← 轻度依赖 userId
4️⃣ Favorite（收藏模块）     ← 轻度依赖 userId
5️⃣ History（播放历史）      ← 最简单
6️⃣ Search（搜索模块）       ← 基于 Music
🚫 Auth / User（最后）

