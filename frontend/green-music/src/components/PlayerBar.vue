<template>
  <div class="player-bar">
    <!-- 左侧：歌曲信息 -->
    <div class="left">
      <img class="cover" :src="song.cover" />
      <div class="info">
        <div class="name">{{ song.name }}</div>
        <div class="artist">{{ song.artist }}</div>
      </div>
    </div>

    <!-- 中间：控制 -->
    <div class="center">
      <button>⏮</button>
      <button class="play">▶</button>
      <button>⏭</button>
    </div>

    <!-- 右侧：功能 -->
    <div class="right">
      <button>🔊</button>
      <button>❤️</button>
      <button>⚙</button>
    </div>
  </div>
</template>

<script setup>
const song = {
  name: '左转灯 (1000 Times +1)',
  artist: '汪苏泷 / 周兴哲',
  cover: 'https://picsum.photos/60'
}
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

