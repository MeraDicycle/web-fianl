<template>
  <div class="player-bar" v-if="currentSong">
    <!-- 左侧：歌曲信息 -->
    <div class="left">
      <img class="cover" :src="currentSong.cover" />
      <div class="info">
        <div class="name">{{ currentSong.name }}</div>
        <div class="artist">{{ currentSong.artists }}</div>
      </div>
    </div>

    <!-- 中间：控制 -->
<div class="center">
  <button @click="prev">⏮</button>
  <button class="play" @click="toggle">
    {{ isPlaying ? '⏸' : '▶' }}
  </button>
  <button @click="next">⏭</button>
</div>


    <!-- 右侧 -->
    <div class="right">
      <button>🔊</button>
      <button>❤️</button>
      <button>⚙</button>
    </div>

    <!-- 真正的播放器 -->
    <audio
      ref="audioRef"
      :src="currentSong.file_url"
      @ended="onEnded"
      @play="onPlay"
    />
  </div>
</template>

<script setup>
import { ref, watch, computed  } from 'vue'
import { usePlayerStore } from '../store/player.js'
import axios from 'axios'

const playerStore = usePlayerStore()
const audioRef = ref(null)

const currentSong = computed(() => playerStore.currentSong)
const isPlaying = computed(() => playerStore.isPlaying)

/** 播放 / 暂停 */
const toggle = () => {
  playerStore.togglePlay()
}

/** 歌曲切换时自动播放 */
watch(
  () => playerStore.currentSong,
  async () => {
    if (audioRef.value) {
      await audioRef.value.play()
    }
  }
)


/** 播放状态变化 */
watch(
  () => playerStore.isPlaying,
  (val) => {
    if (!audioRef.value) return
    if (!val) {
      audioRef.value.pause()
    }
  }
)

const onEnded = () => {
  playerStore.next()
}

let lastRecordedId = null

const onPlay = () => {
  const song = currentSong.value
  if (!song) return
  console.log(song.id)

  // 防止同一首歌重复记录
  if (song.id === lastRecordedId) return

  lastRecordedId = song.id

  // ✅ 真正开始播放，记录历史
  axios.post(`/user/history/${song.id}`)
}
const prev = () => {
  playerStore.prev()
}

const next = () => {
  playerStore.next()
}

watch(
  () => playerStore.currentSong,
  (song) => {
    if (!song) return
    console.log('🎵 currentSong:', song)
    console.log('🎵 audio file_url:', song.file_url)
  }
)


</script>

<style scoped>
.player-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 72px;
  background: #fff;
  border-top: 1px solid #eee;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
  box-sizing: border-box;
  z-index: 1000;
}

/* 左 */
.left {
  display: flex;
  align-items: center;
  gap: 12px;
}

.cover {
  width: 48px;
  height: 48px;
  border-radius: 6px;
}

.info .name {
  font-size: 14px;
  font-weight: 500;
}

.info .artist {
  font-size: 12px;
  color: #666;
}

/* 中 */
.center {
  display: flex;
  align-items: center;
  gap: 16px;
}

.center button {
  background: none;
  border: none;
  font-size: 18px;
  cursor: pointer;
}

.center .play {
  font-size: 22px;
}

/* 右 */
.right {
  display: flex;
  gap: 12px;
}

.right button {
  background: none;
  border: none;
  font-size: 18px;
  cursor: pointer;
}
</style>
