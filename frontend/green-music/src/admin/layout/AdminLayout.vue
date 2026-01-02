<template>
  <el-container class="admin-layout">
    <!-- 左侧菜单 -->
    <el-aside width="200px" class="aside">
      <div class="logo">
        🎵 GreenMusic
      </div>

      <el-menu
        router
        :default-active="$route.path"
        class="menu"
      >
        <el-menu-item index="/admin/music">
          <span>歌曲管理</span>
        </el-menu-item>

        <el-menu-item index="/admin/playlist">
          <span>歌单管理</span>
        </el-menu-item>

        <el-menu-item index="/admin/user">
          <span>用户管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <!-- 右侧内容 -->
    <el-container>
      <!-- 顶部 -->
      <el-header class="header">
        <div class="title">后台管理系统</div>
        <div class="admin-info">
          管理员
        </div>
      </el-header>

      <!-- 主内容区 -->
      <el-main class="main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
</script>

<style scoped>
.admin-layout {
  height: 100vh;
}

/* 左侧 */
.aside {
  background: #001529;
  color: #fff;
}

.logo {
  height: 60px;
  line-height: 60px;
  text-align: center;
  font-size: 18px;
  font-weight: bold;
  color: #1db954;
}

.menu {
  border-right: none;
}

/* 顶部 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #fff;
  border-bottom: 1px solid #eee;
}

.title {
  font-size: 18px;
  font-weight: bold;
}

.admin-info {
  color: #666;
}

/* 内容区 */
.main {
  background: #f5f7fa;
  padding: 20px;
}
</style>
