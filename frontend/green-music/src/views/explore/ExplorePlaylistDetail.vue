<!-- /explore-music/playlist/${id} -->
<template>
  <div class="playlist-detail-page">

    <!-- 顶部信息区 -->
    <div class="playlist-header">
      <img class="cover" :src="playlist.cover" />

      <div class="info">
        <h1 class="title">{{ playlist.title }}</h1>

        <div class="creator">
          <span class="icon-user">👤</span>
          <span>{{ playlist.creator }}</span>
        </div>

        <div class="tags">
          <span class="label">标签：</span>
          <span class="tag" v-for="t in playlist.tags" :key="t">{{ t }}</span>
        </div>

        <div class="stats">
          <span>收藏量：{{ playlist.collectCount }}</span>
        </div>

        <div class="actions">
          <button class="play-all">▶ 播放全部</button>
          <button class="btn">♡ 收藏</button>
          <button class="btn">💬 评论({{ playlist.commentCount }})</button>
        </div>
      </div>
    </div>

    <!-- 歌曲列表 -->
    <div class="song-table">
      <div class="table-header">
        <span class="col-index"></span>
        <span class="col-name">歌曲</span>
        <span class="col-artist">歌手</span>
        <span class="col-album">专辑</span>
        <span class="col-duration">时长</span>
      </div>

      <div
        class="song-row"
        v-for="(song, index) in songs"
        :key="song.id"
        @mouseenter="hoverIndex = index"
        @mouseleave="hoverIndex = -1"
        @click="goSongDetail(song.id)"
      >
        <span class="col-index">
          <span v-if="hoverIndex !== index">{{ index + 1 }}</span>
          <span v-else class="play-icon">▶</span>
        </span>

        <span class="col-name">
          {{ song.name }}
          <span v-if="song.vip" class="vip">VIP</span>
        </span>

        <span class="col-artist">{{ song.artist }}</span>
        <span class="col-album">{{ song.album }}</span>
        <span class="col-duration">{{ song.duration }}</span>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const hoverIndex = ref(-1)


const goSongDetail = (id) => {
  router.push(`/explore-music/song-detail/${id}`)
}

const playlist = {
  cover: 'https://picsum.photos/300?playlist',
  title: '抖音流行热歌 | 听一遍就上瘾',
  creator: '星野一',
  tags: ['背景音乐', '流行'],
  collectCount: '27.6万',
  commentCount: 432
}

const songs = [
  {
    id: 1,
    name: '善后',
    artist: '马师傅',
    album: '善后',
    duration: '04:14',
    vip: true
  },
  {
    id: 2,
    name: '黑白的海',
    artist: '小心空隙 Mind The',
    album: '白鸟投林！',
    duration: '04:44'
  },
  {
    id: 3,
    name: '有时候想变成一只猫 (Live)',
    artist: '张靓颖',
    album: '音乐缘计划2 第8期',
    duration: '03:41'
  },
  {
    id: 4,
    name: '直行不转弯（说不通的谎言）',
    artist: '头不疼',
    album: '直行不转弯',
    duration: '03:30'
  },
  {
    id: 5,
    name: 'You Still',
    artist: 'Lunar Rey',
    album: 'You Still',
    duration: '02:14',
    vip: true
  }
]
</script>

<style scoped>
.playlist-detail-page {
  padding: 30px 40px;
}

/* 顶部 */
.playlist-header {
  display: flex;
  gap: 30px;
  margin-bottom: 40px;
}

.cover {
  width: 220px;
  height: 220px;
  border-radius: 8px;
  object-fit: cover;
}

.info {
  flex: 1;
}

.title {
  font-size: 26px;
  margin-bottom: 10px;
}

.creator {
  color: #666;
  margin-bottom: 10px;
}

.tags {
  margin-bottom: 8px;
}

.tag {
  margin-right: 8px;
  color: #1ece9a;
}

.stats {
  color: #666;
  margin-bottom: 16px;
}

.actions {
  display: flex;
  gap: 12px;
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

/* 表格 */
.song-table {
  border-top: 1px solid #eee;
}

.table-header,
.song-row {
  display: grid;
  grid-template-columns: 60px 2fr 1.5fr 1.5fr 80px;
  align-items: center;
}

.table-header {
  height: 44px;
  color: #999;
  font-size: 14px;
}

.song-row {
  height: 48px;
  cursor: pointer;
}

.song-row:hover {
  background: #f7f7f7;
}

.col-index {
  text-align: center;
}

.play-icon {
  color: #1ece9a;
  font-size: 16px;
}

.vip {
  margin-left: 6px;
  font-size: 12px;
  color: #1ece9a;
  border: 1px solid #1ece9a;
  padding: 0 4px;
  border-radius: 4px;
}
</style>



