<template>
  <div class="my-page">
    <!-- 顶部背景 + 导航 -->

    <div class="my-header">
      <!-- 用户信息 -->
      <div class="user-info">
        <img class="avatar" :src="user.avatar" />
        <div class="user-meta">
          <div class="nickname">{{ user.nickname }}</div>
          <div class="uid">ID：{{ user.id }}</div>
        </div>
      </div>

      <div class="nav">
        <span v-for="item in tabs" :key="item.key" :class="['nav-item', { active: activeTab === item.key }]"
          @click="activeTab = item.key">
          {{ item.label }}
        </span>
      </div>
    </div>

    <!-- 内容区 -->
    <div class="my-content">

      <!-- 我喜欢 -->
      <div v-if="activeTab === 'like'">
        <div class="table-header">
          <span>歌曲</span>
          <span>歌手</span>
          <span>专辑</span>
          <span>时长</span>
        </div>

        <div class="song-row" v-for="(song, index) in likedSongs" :key="song.id" @click="goSongDetail(song.id)">
          <span>{{ index + 1 }}. {{ song.name }}</span>
          <span>{{ song.artist }}</span>
          <span>{{ song.album }}</span>
          <span>{{ song.duration }}</span>
        </div>
      </div>

      <!-- 收藏的歌单 -->
      <div v-if="activeTab === 'collect'">
        <div class="playlist-grid">
          <div class="playlist-card" v-for="p in collectedPlaylists" :key="p.id" @click="goPlayList(p.id)">
            <img :src="p.cover" />
            <div class="name">{{ p.name }}</div>
            <div class="count">{{ p.count }} 首</div>
          </div>
        </div>
      </div>

      <!-- 创建的歌单 -->
      <div v-if="activeTab === 'create'">
        <div class="playlist-grid">
          <div class="playlist-card" v-for="p in createdPlaylists" :key="p.id" @click="goMyPlaylist(p.id)">
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
import { useRouter } from 'vue-router'
const router = useRouter()

const user = ref({
  id: 10001,
  nickname: '蔡健雅',
  avatar: 'https://picsum.photos/120'
})

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
const goPlayList = (id) => {
  router.push(`/explore-music/playlist/${id}`)
}
const goSongDetail = (id) => {
  router.push(`/explore-music/song-detail/${id}`)
}
const goMyPlaylist = (id) => {
  router.push(`/my-playlist/${id}`)
}
</script>

<style scoped>
.my-page {
  background: #fff;
}

/* 顶部 */
.my-header {
  height: 180px;
  background: linear-gradient(120deg, #0f2027, #203a43, #2c5364);
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 20px 40px 10px;
}

.avatar {
  width: 88px;
  height: 88px;
  border-radius: 50%;
  object-fit: cover;

  border: 3px solid rgba(255, 255, 255, 0.8);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.3);
}

.user-meta {
  color: #fff;
}

.nickname {
  font-size: 22px;
  font-weight: 600;
}

.uid {
  margin-top: 4px;
  font-size: 13px;
  color: rgba(255, 255, 255, 0.7);
}

.nav {
  display: flex;
  gap: 40px;
  padding: 0 40px 16px;
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
