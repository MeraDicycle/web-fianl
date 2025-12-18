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

const keyword = ref('')
const searched = ref(false)

const hotKeywords = [
  '周杰伦',
  '华语流行',
  '学习 BGM',
  '周深',
  '电子音乐'
]

const allSongs = [
  { id: 1, name: '晴天', artist: '周杰伦', duration: '04:29' },
  { id: 2, name: '稻香', artist: '周杰伦', duration: '03:42' },
  { id: 3, name: '起风了', artist: '买辣椒也用券', duration: '05:25' },
  { id: 4, name: '光亮', artist: '周深', duration: '04:18' }
]

const results = ref([])

const doSearch = () => {
  searched.value = true
  results.value = allSongs.filter(
    s =>
      s.name.includes(keyword.value) ||
      s.artist.includes(keyword.value)
  )
}

const searchHot = (word) => {
  keyword.value = word
  doSearch()
}
</script>

<style scoped>
.search-page {
  padding: 40px;
}

/* 搜索框 */
.search-box {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-bottom: 40px;
}

.search-box input {
  width: 420px;
  height: 42px;
  padding: 0 14px;
  font-size: 16px;
  border-radius: 8px;
  border: 1px solid #ddd;
}

.search-box button {
  padding: 0 20px;
  background: #1ece9a;
  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
}

/* 热门搜索 */
.hot-search {
  margin-bottom: 40px;
}

.hot-search h3 {
  margin-bottom: 12px;
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
}

.tag:hover {
  background: #1ece9a;
  color: #fff;
}

/* 搜索结果 */
.result h3 {
  margin-bottom: 12px;
}

.result-item {
  display: grid;
  grid-template-columns: 2fr 1.5fr 80px;
  padding: 10px 0;
  border-bottom: 1px solid #eee;
}

.result-item:hover {
  background: #f7f7f7;
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
}

.empty {
  color: #999;
  padding: 20px 0;
}
</style>
