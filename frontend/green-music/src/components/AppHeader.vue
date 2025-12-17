<template>
    <div class="app-header">
        <div class="left">
            <img src="../assets/logo/gm-logo.png" class="gm-logo">
            <h2 class="logo-title">Green Music</h2>
        </div>

        <div class="medium">
            <router-link class="guide" to="/explore-music" >音乐馆</router-link>
            <router-link class="guide" to="/search-music">搜索</router-link>
            <router-link class="guide" to="/my-music">我的</router-link>            
        </div>

        <div class="right">
            <div class="avater">
                111
            </div>
        </div>
    </div>
</template>

<script setup>
</script>

<style>
.app-header {
    width: 100%;
    height: 80px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 40px;
    box-sizing: border-box;
    background-color: #fff;
    border-bottom: 1px solid #eee;
}

.left {
  display: flex;
  align-items: center;
}

.gm-logo {
  width: 56px;
  height: 56px;
  cursor: pointer;
}

.medium {
  display: flex;
  gap: 48px;
  font-size: 20px;
  font-weight: 500;
}

.guide {
  position: relative;
  padding: 6px 0;
  color: #333;
  text-decoration: none;
  cursor: pointer;
  transition: color 0.2s ease;
}

.guide:hover {
  color: var(--hover-color);
}

.guide::after {
  content: '';
  position: absolute;
  left: 0;
  bottom: -6px;
  width: 100%;
  height: 2px;
  background-color: var(--hover-color);
  transform: scaleX(0);
  transition: transform 0.25s ease;
}

.guide:hover::after,
.router-link-exact-active::after {
  transform: scaleX(1);
}

.router-link-exact-active {
  color: var(--hover-color);
  font-weight: 600;
}

.right {
  display: flex;
  align-items: center;
}

.avater {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  background-color: #ccc;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  cursor: pointer;
}

</style>