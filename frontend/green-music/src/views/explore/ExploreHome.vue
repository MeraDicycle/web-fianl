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
        <div
          v-for="item in playlists"
          :key="item.id"
          class="playlist-card"
          @click="goPlaylistDetail(item.id)"
        >
          <img :src="item.cover" />
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

.playlist-section h3:hover{
  color: var(--hover-color);
  cursor: pointer;
}

.playlist-grid {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 20px;
  margin-top: 16px;
}

.playlist-card {
  cursor: pointer;
}

.playlist-card img {
  width: 100%;
  max-width: 300px;
  border-radius: 8px;
}

.playlist-name {
  margin-top: 8px;
  font-weight: 500;
}

.song-section h3:hover {
  color: var(--hover-color);
  cursor: pointer;
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

