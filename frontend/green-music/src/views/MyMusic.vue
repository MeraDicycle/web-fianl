<template>
  <div class="my-page">

    <!-- 顶部背景 + 导航 -->
    <div class="my-header">
      <div class="nav">
        <span
          v-for="item in tabs"
          :key="item.key"
          :class="['nav-item', { active: activeTab === item.key }]"
          @click="activeTab = item.key"
        >
          {{ item.label }}
        </span>
      </div>
    </div>

    <!-- 内容区 -->
    <div class="my-content">

      <!-- 我喜欢 -->
      <div v-if="activeTab === 'like'">
        <div class="action-bar">
          <button class="play-all">▶ 播放全部</button>
          <button class="btn">＋ 添加到</button>
          <button class="btn">⬇ 下载</button>
        </div>

        <div class="table-header">
          <span>歌曲</span>
          <span>歌手</span>
          <span>专辑</span>
          <span>时长</span>
        </div>

        <div
          class="song-row"
          v-for="(song, index) in likedSongs"
          :key="song.id"
        >
          <span>{{ index + 1 }}. {{ song.name }}</span>
          <span>{{ song.artist }}</span>
          <span>{{ song.album }}</span>
          <span>{{ song.duration }}</span>
        </div>
      </div>

      <!-- 收藏的歌单 -->
      <div v-if="activeTab === 'collect'">
        <div class="playlist-grid">
          <div
            class="playlist-card"
            v-for="p in collectedPlaylists"
            :key="p.id"
          >
            <img :src="p.cover" />
            <div class="name">{{ p.name }}</div>
            <div class="count">{{ p.count }} 首</div>
          </div>
        </div>
      </div>

      <!-- 创建的歌单 -->
      <div v-if="activeTab === 'create'">
        <div class="playlist-grid">
          <div
            class="playlist-card"
            v-for="p in createdPlaylists"
            :key="p.id"
          >
            <img :src="p.cover" />
            <div class="name">{{ p.name }}</div>
            <div class="count">{{ p.count }} 首</div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const tabs = [
  { key: 'like', label: '我喜欢' },
  { key: 'collect', label: '收藏的歌单' },
  { key: 'create', label: '我创建的歌单' }
]

const activeTab = ref('like')

const likedSongs = [
  { id: 1, name: '青花', artist: '七元', album: '青花', duration: '03:48' },
  { id: 2, name: '壁上观', artist: '鞠婧祎', album: '壁上观', duration: '03:41' },
  { id: 3, name: '青衣 (DJ版)', artist: '草帽酱 / DJ阿泽', album: '青衣', duration: '05:44' }
]

const collectedPlaylists = [
  {
    id: 1,
    name: '学习必备 BGM',
    count: 58,
    cover: 'https://picsum.photos/200?c1'
  },
  {
    id: 2,
    name: '华语流行精选',
    count: 120,
    cover: 'https://picsum.photos/200?c2'
  }
]

const createdPlaylists = [
  {
    id: 1,
    name: '我的最爱',
    count: 36,
    cover: 'https://picsum.photos/200?m1'
  },
  {
    id: 2,
    name: '夜晚循环',
    count: 24,
    cover: 'https://picsum.photos/200?m2'
  }
]
</script>

<style scoped>
.my-page {
  background: #fff;
}

/* 顶部 */
.my-header {
  height: 160px;
  background: linear-gradient(120deg, #0f2027, #203a43, #2c5364);
  display: flex;
  align-items: flex-end;
}

.nav {
  display: flex;
  gap: 40px;
  padding: 0 40px 20px;
}

.nav-item {
  color: #ddd;
  cursor: pointer;
  font-size: 16px;
}

.nav-item.active {
  color: #1ece9a;
  font-weight: 600;
}

/* 内容 */
.my-content {
  padding: 30px 40px;
}

/* 操作栏 */
.action-bar {
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
}

.btn {
  border: 1px solid #ddd;
  background: #fff;
  padding: 8px 14px;
  border-radius: 6px;
}

/* 表格 */
.table-header,
.song-row {
  display: grid;
  grid-template-columns: 2fr 1.5fr 1.5fr 80px;
  padding: 10px 0;
}

.table-header {
  color: #999;
  border-bottom: 1px solid #eee;
}

.song-row {
  border-bottom: 1px solid #f0f0f0;
}

.song-row:hover {
  background: #f7f7f7;
}

/* 歌单 */
.playlist-grid {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 24px;
}

.playlist-card img {
  width: 100%;
  border-radius: 8px;
}

.name {
  margin-top: 8px;
  font-weight: 500;
}

.count {
  font-size: 12px;
  color: #999;
}
</style>
