<template>
  <div class="rank-page">

    <!-- 榜单头部 -->
    <div class="rank-header">
      <h1 class="title">{{currentRankTitle}}</h1>
      <span class="date">2025-12-18</span>
    </div>

    <!-- 榜单切换 -->
    <div class="rank-tabs">
      <div v-for="tab in rankTabs" :key="tab.key" class="rank-tab" :class="{ active: currentRank === tab.key }"
        @click="currentRank = tab.key">
        {{ tab.name }}
      </div>
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
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const rankTabs = [
  { key: 'hot', name: '🔥 热歌榜' },
  { key: 'pop', name: '🎧 流行指数榜' },
  { key: 'new', name: '🆕 新歌榜' },
]

const currentRank = ref('hot')

const rankData = {
  hot: [
    {
      id: 1,
      name: '左转灯 (1000 Times +1)',
      artist: '汪苏泷 / Eric周兴哲',
      duration: '03:14',
      cover: 'https://picsum.photos/80?1'
    },
    {
      id: 2,
      name: '原来是这样～',
      artist: '周深',
      duration: '03:30',
      cover: 'https://picsum.photos/80?2'
    },
  ],
  pop: [
    {
      id: 3,
      name: '孤独患者',
      artist: '陈奕迅',
      duration: '04:02',
      cover: 'https://picsum.photos/80?3'
    }
  ],
  new: [
    {
      id: 4,
      name: '新世界',
      artist: '华晨宇',
      duration: '03:58',
      cover: 'https://picsum.photos/80?4'
    }
  ]
}

const songs = computed(() => rankData[currentRank.value])
const currentRankTitle = computed(() => {
  return rankTabs.find(tab => tab.key === currentRank.value)?.name || ''
})
const goSongDetail = (id) => {
  router.push(`/explore-music/song-detail/${id}`)
}
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


.rank-tabs {
  display: flex;
  gap: 16px;
  margin-bottom: 20px;
}

.rank-tab {
  padding: 8px 18px;
  border-radius: 20px;
  background: #f0f0f0;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.25s ease;
}

.rank-tab:hover {
  background: #eaf7f0;
}

.rank-tab.active {
  background: #1db954;
  color: #fff;
  font-weight: 600;
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
