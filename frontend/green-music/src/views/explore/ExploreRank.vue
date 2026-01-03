<template>
  <div class="rank-page">

    <!-- 榜单头部 -->
    <div class="rank-header">
      <h1 class="title">新歌榜</h1>
      <span class="date">2025-12-18</span>
    </div>

    <!-- 表头 -->
    <div class="rank-table-header">
      <span class="col-rank"></span>
      <span class="col-song">歌曲</span>
      <span class="col-artist">歌手</span>
      <span class="col-duration">时长</span>
    </div>

    <!-- 榜单列表 -->
    <div class="rank-row" v-for="(song, index) in songs" :key="song.id" @click="goSongDetail(song.id)">
      <!-- 排名 -->
      <div class="col-rank">
        <div class="rank-num">{{ index + 1 }}</div>
      </div>

      <!-- 歌曲 -->
      <div class="col-song">
        <img class="cover" :src="song.cover" />
        <span class="song-name">
          {{ song.name }}
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
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const songs = ref([])

const formatDuration = (sec) => {
  if (!sec && sec !== 0) return ''
  const m = Math.floor(sec / 60)
  const s = sec % 60
  return `${String(m).padStart(2, '0')}:${String(s).padStart(2, '0')}`
}

const loadRank = async () => {
  try {
    const res = await axios.get('/rank')
    const list = res.data.data || []

    songs.value = list.map(item => ({
      id: item.id,
      name: item.title,
      artist: item.artist,
      cover: item.coverUrl,
      duration: formatDuration(item.durationSec)
    }))
  } catch (e) {
    console.error('load rank error:', e)
  }
}

const goSongDetail = (id) => {
  router.push(`/explore-music/song-detail/${id}`)
}

onMounted(() => {
  loadRank()
})
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
  background: #f4fbf7;
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

/* 其他 */
.col-artist {
  color: #333;
}

.col-duration {
  color: #666;
}
</style>
