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

        <button class="btn" @click="togglePlaylistLike">
          {{ playlist.liked ? '♥ 已收藏' : '♡ 收藏' }}
        </button>
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
import { ref, onMounted} from 'vue'
import { useRouter, useRoute } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const route = useRoute()

const hoverIndex = ref(-1)

const goSongDetail = (id) => {
  router.push(`/explore-music/song-detail/${id}`)
}
const playlist = ref({
  cover: '',
  title: '',
  creator: '',
  tags: [],
  collectCount: 0,
  commentCount: 0,
  liked: false,
})
const songs = ref([])

const formatDuration = (sec) => {
  if (!sec && sec !== 0) return ''
  const m = Math.floor(sec / 60)
  const s = sec % 60
  return `${String(m).padStart(2, '0')}:${String(s).padStart(2, '0')}`
}

const loadPlaylistDetail = async () => {
  try {
    const id = route.params.id

    const res = await axios.get(`http://localhost:8080/playlist/${id}`)

    const data = res.data.data

    console.log(data.liked)

    /* 歌单信息映射 */
    playlist.value = {
      cover: data.playlist.coverUrl,
      title: data.playlist.name,
      creator: `用户 ${data.playlist.userId}`, // 当前接口没返回昵称，先占位
      tags: data.playlist.category
        ? [data.playlist.category]
        : [],
      collectCount: data.playlist.liked ? 1 : 0, // 占位
      commentCount: 0,
      liked: data.playlist.liked === true
    }

    /* 歌曲列表映射 */
    songs.value = data.musicList.map(item => ({
      id: item.id,
      name: item.title,
      artist: item.artist,
      album: '-', // 后端未提供
      duration: formatDuration(item.durationSec),
      vip: false
    }))
  } catch (e) {
    console.error('load playlist detail error:', e)
    songs.value = []
  }
}

const togglePlaylistLike = async () => {
  try {
    const playlistId = route.params.id

    await axios.post(
      `http://localhost:8080/favorite/playlist/${playlistId}`
    )

    // ⭐ 立刻更新前端状态（不等后端再查）
    playlist.value.liked = !playlist.value.liked

    // 收藏数简单联动（占位）
    if (playlist.value.liked) {
      playlist.value.collectCount += 1
    } else {
      playlist.value.collectCount = Math.max(
        0,
        playlist.value.collectCount - 1
      )
    }
  } catch (e) {
    console.error('toggle playlist like error:', e)
  }
}


onMounted(() => {
  loadPlaylistDetail()
})


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
  padding: 10px 18px;
  border-radius: 999px;
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
}
.btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 16px rgb(0, 0, 0, 0.15)
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
  background: var(--hover-light-color);
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



