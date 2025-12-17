<template>
    <nav class="sub-nav">
      <router-link to="/explore-music/home" class="guide">主页</router-link>
      <router-link to="/explore-music/playlist" class="guide">歌单</router-link>
      <router-link to="/explore-music/rank" class="guide">排行榜</router-link>
    </nav>
  <main class="page">
    <router-view />
  </main>
</template>

<script setup>
import AppHeader from '../components/AppHeader.vue'
</script>

<style>
    .sub-nav {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 50px;
        padding-left: 100px;
    }

    .guide {
        padding: 10px;
        padding-bottom: 2px;
        text-decoration: none;
    }
</style>