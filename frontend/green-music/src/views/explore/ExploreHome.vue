<!-- /explore-music/home -->
<template>
  <div class="home-page">

    <!-- 欢迎 / 推荐语 -->
    <section class="welcome">
      <h2>为你推荐</h2>
      <p>发现你可能喜欢的音乐</p>
    </section>

    <!-- 推荐歌单 -->
    <section class="playlist-section">
      <h3 @click="goPlaylist">推荐歌单 >>></h3>

      <div class="playlist-grid">
        <div v-for="item in playlists" :key="item.id" class="playlist-card" @click="goPlaylistDetail(item.id)">
          <div class="cover-wrapper">
            <img :src="item.cover" />
            <div class="play-btn">▶</div>
          </div>
          <div class="playlist-name">{{ item.name }}</div>
        </div>
      </div>
    </section>

    <!-- 热门歌曲 -->
    <section class="song-section">
      <h3 @click="goRank">热门歌曲 >>></h3>

      <ul class="song-list">
        <li v-for="song in songs" :key="song.id" @click="goSongDetail(song.id)">
          <span class="song-name">{{ song.name }}</span>
          <span class="song-artist">{{ song.artist }}</span>
        </li>
      </ul>
    </section>

  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
const router = useRouter()
const goPlaylistDetail = (id) => {
  router.push(`/explore-music/playlist/${id}`)
}
const goPlaylist = () => {
  router.push(`/explore-music/playlist`)
}
const goSongDetail = (id) => {
  router.push(`/explore-music/song-detail/${id}`)
}
const goRank = () => {
  router.push(`/explore-music/rank`)
}
const playlists = [
  {
    id: 1,
    name: '学习必备 · 纯音乐',
    cover: 'https://picsum.photos/200?1'
  },
  {
    id: 2,
    name: '华语流行精选',
    cover: 'https://picsum.photos/200?2'
  },
  {
    id: 3,
    name: '深夜治愈系',
    cover: 'https://picsum.photos/200?3'
  },
  {
    id: 4,
    name: '深夜治愈系',
    cover: 'https://picsum.photos/200?3'
  },
  {
    id: 5,
    name: '深夜治愈系',
    cover: 'https://picsum.photos/200?3'
  }
]

const songs = [
  { id: 1, name: '晴天', artist: '周杰伦' },
  { id: 2, name: '稻香', artist: '周杰伦' },
  { id: 3, name: '起风了', artist: '买辣椒也用券' }
]
</script>


<style scoped>
.home-page {
  padding: 24px 40px;
}

section {
  margin-bottom: 40px;
}

.welcome h2 {
  font-size: 24px;
  margin-bottom: 4px;
}

.welcome p {
  color: #888;
}

.playlist-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(160px, 1fr));
  gap: 20px;
}

/* 歌单卡片 */
.playlist-card {
  cursor: pointer;
  transition: transform 0.25s ease, box-shadow 0.25s ease;
}

.playlist-card:hover {
  transform: translateY(-6px);
}

/* 封面 */
.cover-wrapper {
  position: relative;
  border-radius: 12px;
  overflow: hidden;
}

.cover-wrapper img {
  width: 100%;
  aspect-ratio: 1 / 1;
  object-fit: cover;
  display: block;
}

/* 播放按钮 */
.play-btn {
  position: absolute;
  right: 12px;
  bottom: 12px;
  width: 44px;
  height: 44px;
  border-radius: 50%;
  background: #1db954;
  color: #000;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;

  opacity: 0;
  transform: translateY(8px) scale(0.9);
  transition: all 0.25s ease;
}

/* hover 时显示播放按钮 */
.playlist-card:hover .play-btn {
  opacity: 1;
  transform: translateY(0) scale(1);
}

/* 歌单名 */
.playlist-name {
  margin-top: 8px;
  font-size: 14px;
  color: black;
  text-align: center;
}

section h3 {
  margin: 24px 0 12px;
  font-size: 18px;
  cursor: pointer;
  transition: color 0.2s;
}

section h3:hover {
  color: #1db954;
}

.song-list {
  margin-top: 12px;
  list-style: none;
  padding: 0;
}

.song-list li {
  display: flex;
  justify-content: space-between;
  padding: 8px 0;
  border-bottom: 1px solid #eee;
}

.song-list li:hover {
  color: var(--hover-color);
  cursor: pointer;
}

.song-name {
  font-weight: 500;
}

.song-artist {
  color: #999;
}
</style>
