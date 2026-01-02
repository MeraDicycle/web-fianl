<template>
  <div style="padding: 24px; max-width: 720px;">
    <h2>Audio 资源测试</h2>

    <div style="display:flex; gap: 8px; align-items:center; margin: 12px 0;">
      <span>URL：</span>
      <input
        v-model="url"
        style="flex:1; padding: 8px;"
        placeholder="例如 /audio/test.mp3 或 http://localhost:8080/audio/test.mp3"
      />
      <button @click="check" style="padding: 8px 12px;">测试可访问</button>
    </div>

    <div style="margin: 12px 0; font-family: monospace; white-space: pre-wrap;">
      {{ statusText }}
    </div>

    <!-- 只用 audio 验证是否能加载/播放 -->
    <audio
      :src="url"
      controls
      preload="metadata"
      @loadedmetadata="onLoadedMeta"
      @canplay="onCanPlay"
      @play="onPlay"
      @error="onError"
      style="width: 100%; margin-top: 8px;"
    />

    <p style="margin-top: 12px; color:#666;">
      提示：如果你前端是 Vite(5173)，而后端是 8080，建议先把 URL 写成
      <code>http://localhost:8080/audio/test.mp3</code> 来排除代理因素。
    </p>
  </div>
</template>

<script setup>
import { ref } from 'vue'

/**
 * 默认给一个最常见的路径：
 * - 如果你后端提供的是 /audio/** 静态资源：用 /audio/test.mp3
 * - 如果前端和后端不同源：改成 http://localhost:8080/audio/test.mp3
 */
const url = ref('/audio/test.mp3')
const statusText = ref('')

// 用 fetch 先探测一下：是否 200、Content-Type 是啥
const check = async () => {
  statusText.value = 'checking...'
  try {
    const res = await fetch(url.value, { method: 'GET' })
    const ct = res.headers.get('content-type')
    statusText.value =
      `HTTP: ${res.status} ${res.statusText}\n` +
      `Content-Type: ${ct || '(none)'}\n` +
      `Final URL: ${res.url || '(same as input)'}`
  } catch (e) {
    statusText.value = `Fetch error: ${String(e)}`
  }
}

// audio 事件：帮助你判断卡在哪
const onLoadedMeta = (e) => {
  const a = e.target
  statusText.value += `\n[loadedmetadata] duration=${a.duration}s`
}
const onCanPlay = () => {
  statusText.value += `\n[canplay] audio can play`
}
const onPlay = () => {
  statusText.value += `\n[play] started`
}
const onError = (e) => {
  const a = e.target
  // MediaError codes: 1 aborted, 2 network, 3 decode, 4 src not supported
  statusText.value += `\n[error] mediaErrorCode=${a?.error?.code ?? 'unknown'}`
}
</script>
