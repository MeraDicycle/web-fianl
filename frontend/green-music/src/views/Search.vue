<!-- /search-music -->
<template>
  <div class="search-page">

    <!-- 搜索框 -->
    <div class="search-box">
      <input
        v-model="keyword"
        type="text"
        placeholder="搜索歌曲 / 歌手 / 歌单"
        @keyup.enter="doSearch"
      />
      <button @click="doSearch">搜索</button>
    </div>

    <!-- 热门搜索 -->
    <div class="hot-search">
      <h3>热门搜索</h3>
      <div class="tags">
        <span
          class="tag"
          v-for="item in hotKeywords"
          :key="item"
          @click="searchHot(item)"
          @keyup.enter="searchHot(item)"
        >
          {{ item }}
        </span>
      </div>
    </div>

    <!-- 搜索结果 -->
    <div v-if="searched" class="result">
      <h3>搜索结果</h3>

      <div
        class="result-item"
        v-for="song in results"
        :key="song.id"
        @click="goSongDetail(song.id)"
      >
        <span class="name">{{ song.name }}</span>
        <span class="artist">{{ song.artist }}</span>
        <span class="duration">{{ song.duration }}</span>
      </div>

      <div v-if="results.length === 0" class="empty">
        没有找到相关内容
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()

const keyword = ref('')
const searched = ref(false)
const results = ref([])

const hotKeywords = [
  '周杰伦',
  '华语流行',
  '学习 BGM',
  '周深',
  '电子音乐'
]

const goSongDetail = (id) => {
  router.push(`/explore-music/song-detail/${id}`)
}

const doSearch = async () => {
  if (!keyword.value.trim()) return

  searched.value = true

  try {
    const res = await axios.get('http://localhost:8080/search', {
      params: {
        keyword: keyword.value
      }
    })

    const list = res.data.data || []

    results.value = list.map(item => ({
      id: item.id,
      name: item.title,
      artist: item.artist,
      duration: formatDuration(item.durationSec)
    }))
  } catch (e) {
    console.error('search error:', e)
    results.value = []
  }
}

const searchHot = (word) => {
  keyword.value = word
  doSearch()
}

const formatDuration = (sec) => {
  if (!sec && sec !== 0) return ''
  const m = Math.floor(sec / 60)
  const s = sec % 60
  return `${String(m).padStart(2, '0')}:${String(s).padStart(2, '0')}`
}

</script>


<style scoped>
.search-page {
  padding: 40px;
  min-height: 100%;
}

/* 搜索框 */
.search-box {
  display: flex;
  justify-content: center;
  gap: 12px;
  margin-bottom: 36px;
}

.search-box input {
  width: 460px;
  height: 46px;
  padding: 0 16px;
  font-size: 16px;

  border-radius: 999px;
  border: 1px solid #dfeee7;
  outline: none;

  transition: box-shadow 0.2s, border-color 0.2s;
}

.search-box input:focus {
  border-color: #1db954;
  box-shadow: 0 0 0 3px rgba(29, 185, 84, 0.15);
}


.search-box button {
  padding: 0 26px;
  background: #1db954;
  color: #fff;
  border: none;
  border-radius: 999px;
  font-size: 15px;
  cursor: pointer;

  transition: transform 0.2s, box-shadow 0.2s;
}

.search-box button:hover {
  transform: translateY(-1px);
  box-shadow: 0 6px 14px rgba(29, 185, 84, 0.35);
}


/* 热门搜索 */
.hot-search {
  background: #fff;
  padding: 20px 24px;
  border-radius: 16px;
  margin-bottom: 36px;

  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.05);
}

.hot-search h3 {
  margin-bottom: 14px;
  font-size: 16px;
}


.tags {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
}

.tag {
  padding: 6px 14px;
  border-radius: 20px;
  background: #f5f5f5;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.2s;
}

.tag:hover {
  background: #1ece9a;
  color: #fff;
  transform: translateY(-3px);
}

/* 搜索结果 */
.result {
  background: #fff;
  padding: 20px 24px;
  border-radius: 16px;

  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.05);
}

.result h3 {
  margin-bottom: 12px;
  font-size: 16px;
}


.result-item {
  display: grid;
  grid-template-columns: 2fr 1.5fr 80px;
  align-items: center;

  padding: 10px 12px;
  border-radius: 8px;
  cursor: pointer;

  transition: background 0.2s, transform 0.2s;
}

.result-item:hover {
  background: #f0faf5;
  transform: translateX(4px);
}


.name {
  font-weight: 500;
}

.artist {
  color: #666;
}

.duration {
  color: #999;
  text-align: right;
  font-size: 13px;
}

.empty {
  color: #999;
  padding: 30px 0;
  text-align: center;
  font-size: 14px;
}
</style>
