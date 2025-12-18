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
          <button class="play">▶ 播放</button>
          <button class="btn">智能曲谱</button>
          <button class="btn">♡ 收藏</button>
          <button class="btn">💬 评论({{ song.commentCount }})</button>
          <button class="btn">··· 更多</button>
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

  </div>
</template>


<script setup>
const song = {
  name: '溯 (Reverse) feat. 马吟吟',
  artists: 'CORSAK胡梦周 / 马吟吟',
  album: '溯 (Reverse) feat. 马吟吟',
  language: '国语',
  genre: 'Electronica',
  company: 'LIQUID STATE',
  releaseDate: '2018-07-27',
  commentCount: 54114,
  cover: 'https://picsum.photos/300?reverse'
}

const lyrics = [
  '溯 (Reverse) feat. 马吟吟 - CORSAK胡梦周 / 马吟吟',
  '',
  '词：CORSAK胡梦周',
  '曲：CORSAK胡梦周 / Gunnar Greve / Daniel James Nije / Lars Kristian Rosness / Edvard Normann / Fredrik Borch Olsen',
  'Produced by：CORSAK胡梦周 / Mere Music',
  'Executive produced by：Gunnar Greve',
  'Vocals by：CORSAK胡梦周 / 马吟吟',
  'Vocal recording：刘俊杰',
  'Recorded at：MER Studio / SMG Studio Shanghai',
  'Mixed by：Joakim Soderstrom',
  'Mastering by：Bjorn Engelmann at Cuttingroom',
  '',
  '总想要透过你眼睛',
  '去找寻最原始的野性',
  '没想到最后却闯进',
  '一整座森林的宁静',
  '',
  '你呼吸',
  '靠近',
  '我屏住呼吸'
]
</script>

<style scoped>
.song-detail-page {
  padding: 30px 40px;
}

/* 顶部 */
.song-header {
  display: flex;
  gap: 30px;
  margin-bottom: 40px;
}

.cover {
  width: 260px;
  height: 260px;
  border-radius: 8px;
  object-fit: cover;
}

.info {
  flex: 1;
}

.title {
  font-size: 28px;
  margin-bottom: 10px;
}

.artists {
  color: #666;
  margin-bottom: 14px;
}

.meta {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 6px 20px;
  color: #444;
  font-size: 14px;
  margin-bottom: 20px;
}

.actions {
  display: flex;
  gap: 12px;
}

.play {
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
  padding: 8px 14px;
  border-radius: 6px;
  cursor: pointer;
}

/* 歌词 */
.lyrics-section {
  margin-top: 20px;
}

.lyrics-title {
  font-size: 20px;
  margin-bottom: 12px;
}

.lyrics {
  line-height: 1.8;
  color: #333;
  white-space: pre-line;
}

.lyrics p {
  margin: 2px 0;
}
</style>

