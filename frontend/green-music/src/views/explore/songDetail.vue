<!-- /explore-music/song-detail -->
<template>
  <div class="song-detail-page">

    <!-- 顶部信息区 -->
    <div class="song-header">
      <img class="cover" :src="song.cover" />

      <div class="info">
        <h1 class="title">{{ song.name }}</h1>

        <div class="artists">
          <span class="icon">👤</span>
          <span>{{ song.artists }}</span>
        </div>

        <div class="meta">
          <div>专辑：{{ song.album }}</div>
          <div>语种：{{ song.language }}</div>
          <div>流派：{{ song.genre }}</div>
          <div>唱片公司：{{ song.company }}</div>
          <div>发行时间：{{ song.releaseDate }}</div>
        </div>

        <div class="actions">
          <button class="play" @click="playSong">▶ 播放</button>
          <button class="btn" @click="toggleLike">
            {{ song.liked ? '♥ 已收藏' : '♡ 收藏' }}
          </button>
          <button class="btn" @click="showAddTo = true" @click.stop>➕添加到</button>

          <div v-if="showAddTo" class="add-to-popover show">
            <div class="popover-header">播放列表</div>

            <div class="playlist-list">
              <div class="playlist-item" v-for="pl in myPlaylists" :key="pl.id" 
              :class="{ disabled: pl.disabled }"
              @click="!pl.disabled && addToPlaylist(pl)">
                <span>{{ pl.name }}</span>
                <!-- <span class="count">{{ pl.count }} 首</span> -->
              </div>
            </div>

            <div class="create-playlist" @click="createPlaylist">
              ➕ 添加到新歌单
            </div>
          </div>

        </div>
      </div>
    </div>

    <!-- 歌词区 -->
    <div class="lyrics-section">
      <h2 class="lyrics-title">歌词</h2>

      <div class="lyrics">
        <p v-for="(line, index) in lyrics" :key="index">
          {{ line }}
        </p>
      </div>
    </div>

    <!-- 添加到歌单弹窗 -->

  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import {usePlayerStore} from '../../store/player'

const playerStore = usePlayerStore()

const route = useRoute()

const showAddTo = ref(false)
const song = ref({
  name: '',
  artists: '',
  album: '-',
  language: '-',
  file_url: '-',
  genre: '-',
  company: '-',
  releaseDate: '-',
  cover: '',
  liked: false
})
const lyrics = ref([])
const myPlaylists = ref([])
const rawSong = ref(null)


// 关闭“添加到歌单”浮层
document.addEventListener('click', () => {
  showAddTo.value = false
})

// 加载歌曲详情
const loadSongDetail = async () => {
  try {
    const id = route.params.id

    const res = await axios.get(`/music/${id}`)
    const data = res.data.data

    const rawUrl = data.fileUrl
    const fileUrl = rawUrl
      ? (rawUrl.startsWith('http')
          ? rawUrl
          : `http://localhost:8080${rawUrl}`)
      : ''

    song.value = {
      id: id,
      name: data.title,
      artists: data.artist,
      file_url: fileUrl,
      album: '-',               // 后端未提供
      language: '-',            // 后端未提供
      genre: data.tags || '-',  // 用 tags 兜底
      company: '-',             // 后端未提供
      releaseDate: '-',         // 后端未提供
      cover: data.coverUrl,
      liked: data.liked
    }
    console.log(song.value.id)

    lyrics.value = data.lyric
      ? data.lyric.split('\n')
      : ['暂无歌词']
  } catch (e) {
    console.error('load song detail error:', e)
  }
}

// 收藏 / 取消收藏
const toggleLike = async () => {
  try {
    const id = route.params.id
    await axios.post(`/favorite/music/${id}`)
    song.value.liked = !song.value.liked
  } catch (e) {
    console.error('toggle like error:', e)
  }
}

onMounted(() => {
  loadSongDetail()
  loadMyPlaylists()
})



const addToPlaylist = async (playlist) => {
  try {
    const musicId = route.params.id

    await axios.post(
      `/playlist/${playlist.id}/music/${musicId}`
    )

    playlist.disabled = true
    showAddTo.value = false
  } catch (e) {
    console.error('add to playlist error:', e)
  }
}

const createPlaylist = async () => {
  const name = prompt('请输入歌单名称')
  if (!name) return

  try {
    await axios.post('/playlist/create', {
      name,
      description: '',
      coverUrl: 'https://picsum.photos/300?new',
      category: ''
    })

    loadMyPlaylists()
  } catch (e) {
    console.error('create playlist error:', e)
  }
}
const loadMyPlaylists = async () => {
  try {
    const musicId = route.params.id

    const res = await axios.get('/playlist/my')
    const list = res.data.data || []

    // 并行检查是否已加入
    const playlists = await Promise.all(
      list.map(async (item) => {
        const existsRes = await axios.get(
          `/playlist/${item.id}/music/${musicId}/exists`
        )

        return {
          id: item.id,
          name: item.name,
          count: 0,
          disabled: existsRes.data.data === true
        }
      })
    )

    myPlaylists.value = playlists
  } catch (e) {
    console.error('load my playlists error:', e)
  }
}

const playSong = () => {
  playerStore.play(song)
}

</script>


<style scoped>
.song-detail-page {
  padding: 30px 40px;
}

/* 顶部 */
.song-header {
  display: flex;
  gap: 32px;
  margin-bottom: 40px;

  padding: 28px;
  border-radius: 16px;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.05);
}

.cover {
  width: 260px;
  height: 260px;
  border-radius: 12px;
  object-fit: cover;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
}


.info {
  flex: 1;
}

.title {
  font-size: 30px;
  font-weight: 700;
  margin-bottom: 10px;
}

.artists {
  color: #666;
  font-size: 15px;
  margin-bottom: 18px;
}


.meta {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 6px 24px;
  color: #555;
  font-size: 14px;
  margin-bottom: 24px;
}


.actions {
  display: flex;
  gap: 14px;
  position: relative;
}

.play {
  background: #1db954;
  color: #fff;
  border: none;
  padding: 10px 22px;
  border-radius: 999px;
  font-size: 14px;
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
}

.play:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 16px rgba(29, 185, 84, 0.4);
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

.add-to-popover {
  position: absolute;
  top: 42px;
  left: 160px;
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.45);
  border-radius: 10px;
  overflow: hidden;
  background: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: left;

  opacity: 0;
  transform: translateY(-6px) scale(0.96);
  transform-origin: top left;

  transition:
    opacity 0.18s ease-out,
    transform 0.18s cubic-bezier(0.22, 1, 0.36, 1);
}

.add-to-popover.show {
  opacity: 1;
  transform: translateY(0) scale(1);
}


.popover-header {
  padding: 10px 12px;
  color: black;
  border-bottom: 1px solid #eee;
}

.popover-header:hover {
  background: #f5f7f9;
}

.playlist-list {
  max-height: 160px;
  overflow-y: auto;
}

.playlist-item {
  padding: 10px 12px;
  display: flex;
  justify-content: space-between;
  cursor: pointer;
}

.playlist-item:hover {
  background: #f5f7f9;
}

.playlist-item.disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.playlist-item.disabled:hover {
  background: transparent;
}

.create-playlist {
  padding: 10px 12px;
  border-top: 1px solid #eee;
  cursor: pointer;
  color: #1db954;
}



/* 歌词 */
.lyrics-section {
  background: #fff;
  padding: 24px 28px;
  border-radius: 16px;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.05);
}

.lyrics-title {
  font-size: 20px;
  margin-bottom: 16px;
}

.lyrics {
  line-height: 1.9;
  color: #333;
  white-space: pre-line;
  font-size: 15px;
}

.lyrics p {
  margin: 4px 0;
}
</style>
