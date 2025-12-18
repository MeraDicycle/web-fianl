<template>
  <div class="rank-page">

    <!-- 榜单头部 -->
    <div class="rank-header">
      <h1 class="title">流行指数榜</h1>
      <span class="date">2025-12-18</span>
      <span class="rule">榜单规则</span>
    </div>

    <!-- 操作区 -->
    <div class="rank-actions">
      <button class="play-all">▶ 播放全部</button>
      <button class="btn">＋ 添加到</button>
      <button class="btn">⬇ 下载</button>
      <button class="btn">批量操作</button>
      <button class="btn">💬 评论({{ commentCount }})</button>
    </div>

    <!-- 表头 -->
    <div class="rank-table-header">
      <span class="col-rank"></span>
      <span class="col-song">歌曲</span>
      <span class="col-artist">歌手</span>
      <span class="col-duration">时长</span>
    </div>

    <!-- 榜单列表 -->
    <div
      class="rank-row"
      v-for="(song, index) in songs"
      :key="song.id"
    >
      <!-- 排名 -->
      <div class="col-rank">
        <div class="rank-num">{{ index + 1 }}</div>
        <div class="rank-up">↑ {{ song.up }}%</div>
      </div>

      <!-- 歌曲 -->
      <div class="col-song">
        <img class="cover" :src="song.cover" />
        <span class="song-name">
          {{ song.name }}
          <span v-if="song.mv" class="mv">MV</span>
        </span>
      </div>

      <!-- 歌手 -->
      <div class="col-artist">{{ song.artist }}</div>

      <!-- 时长 -->
      <div class="col-duration">{{ song.duration }}</div>
    </div>

  </div>
</template>

<script setup>
const commentCount = 25754

const songs = [
  {
    id: 1,
    name: '左转灯 (1000 Times +1) (Live)',
    artist: '汪苏泷 / Eric周兴哲',
    duration: '03:14',
    up: 112,
    mv: true,
    cover: 'https://picsum.photos/80?1'
  },
  {
    id: 2,
    name: '原来是这样～',
    artist: '周深',
    duration: '03:30',
    up: 89,
    mv: true,
    cover: 'https://picsum.photos/80?2'
  },
  {
    id: 3,
    name: '深海漫游指南',
    artist: '梓渝',
    duration: '03:30',
    up: 81,
    cover: 'https://picsum.photos/80?3'
  },
  {
    id: 4,
    name: '趁黎明来临前说爱吧',
    artist: '梓渝',
    duration: '03:49',
    up: 76,
    mv: true,
    cover: 'https://picsum.photos/80?4'
  }
]
</script>

<style scoped>
.rank-page {
  padding: 30px 40px;
}

/* 头部 */
.rank-header {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 20px;
}

.title {
  font-size: 26px;
}

.date {
  color: #666;
}

.rule {
  color: #1ece9a;
  cursor: pointer;
}

/* 操作区 */
.rank-actions {
  display: flex;
  gap: 12px;
  margin-bottom: 20px;
}

.play-all {
  background: #1ece9a;
  color: #fff;
  border: none;
  padding: 8px 18px;
  border-radius: 6px;
  cursor: pointer;
}

.btn {
  background: #fff;
  border: 1px solid #ddd;
  padding: 8px 14px;
  border-radius: 6px;
  cursor: pointer;
}

/* 表头 */
.rank-table-header {
  display: grid;
  grid-template-columns: 120px 3fr 2fr 100px;
  color: #999;
  height: 40px;
  align-items: center;
  border-bottom: 1px solid #eee;
}

/* 行 */
.rank-row {
  display: grid;
  grid-template-columns: 120px 3fr 2fr 100px;
  align-items: center;
  height: 64px;
  cursor: pointer;
}

.rank-row:hover {
  background: #f7f7f7;
}

/* 排名 */
.col-rank {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.rank-num {
  font-size: 20px;
  font-weight: 600;
}

.rank-up {
  font-size: 12px;
  color: #1ece9a;
}

/* 歌曲 */
.col-song {
  display: flex;
  align-items: center;
  gap: 12px;
}

.cover {
  width: 48px;
  height: 48px;
  border-radius: 4px;
}

.song-name {
  font-weight: 500;
}

.mv {
  margin-left: 6px;
  font-size: 12px;
  color: #1ece9a;
  border: 1px solid #1ece9a;
  padding: 0 4px;
  border-radius: 4px;
}

/* 其他 */
.col-artist {
  color: #333;
}

.col-duration {
  color: #666;
}
</style>
