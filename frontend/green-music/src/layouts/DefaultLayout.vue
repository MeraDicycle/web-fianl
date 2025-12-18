<template>
  <AppHeader />
  <main class="page">
    <router-view />
    <PlayerBar />
  </main>
</template>

<script setup>
import AppHeader from '../components/AppHeader.vue'
import PlayerBar from '../components/PlayerBar.vue';
</script>

<style scoped>
.page {
  padding-bottom: 80px;
}
</style>
