<template>
  <div class="my-page">
    <!-- 顶部背景 + 导航 -->
    <div class="my-header">
      <!-- 用户信息 -->
      <div class="user-info">
        <img class="avatar" :src="user.avatar" />
        <div class="user-meta">
          <div class="nickname">{{ user.nickname }} <el-icon class="setting" @click="openEdit">
              <Setting />
            </el-icon></div>
          <div class="uid">ID：{{ user.id }}</div>
        </div>
      </div>

      <div class="nav">
        <span v-for="item in tabs" :key="item.key" :class="['nav-item', { active: activeTab === item.key }]"
          @click="activeTab = item.key">
          {{ item.label }}
        </span>
      </div>
    </div>

    <!-- 内容区 -->
    <div class="my-content">
      <!-- 我喜欢 -->
      <div v-if="activeTab === 'like'">
        <div class="table-header">
          <span>歌曲</span>
          <span>歌手</span>
          <span>专辑</span>
          <span>时长</span>
        </div>

        <div class="song-row" v-for="(song, index) in likedSongs" :key="song.id" @click="goSongDetail(song.id)">
          <span>{{ index + 1 }}. {{ song.name }}</span>
          <span>{{ song.artist }}</span>
          <span>{{ song.album }}</span>
          <span>{{ song.duration }}</span>
        </div>
      </div>

      <!-- 收藏的歌单 -->
      <div v-if="activeTab === 'collect'">
        <div class="playlist-grid">
          <div class="playlist-card" v-for="p in collectedPlaylists" :key="p.id" @click="goPlayList(p.id)">
            <img :src="p.cover" />
            <div class="play-btn">▶</div>
            <div class="name">{{ p.name }}</div>
            <div class="count">{{ p.count }} 首</div>
          </div>
        </div>
      </div>

      <!-- 创建的歌单 -->
      <div v-if="activeTab === 'create'">
        <div class="playlist-grid">
          <div class="playlist-card" v-for="p in createdPlaylists" :key="p.id" @click="goMyPlaylist(p.id)">
            <img :src="p.cover" />
            <div class="name">{{ p.name }}</div>
            <div class="count">{{ p.count }} 首</div>
          </div>
        </div>
      </div>

      <!-- 播放历史 -->
      <div v-if="activeTab === 'history'">
      <div class="table-header">
        <span>歌曲</span>
        <span>歌手</span>
        <span>专辑</span>
        <span>时长</span>
      </div>

      <div class="song-row" v-for="(song, index) in historySongs" :key="song.id" @click="goSongDetail(song.id)">
        <span>{{ index + 1 }}. {{ song.name }}</span>
        <span>{{ song.artist }}</span>
        <span>{{ song.album }}</span>
        <span>{{ song.duration }}</span>
      </div>
    </div>
    </div>


    <!-- 修改资料弹窗 -->
    <el-dialog v-model="showEdit" title="修改个人资料" width="400px">
      <div class="edit-form">
        <!-- 头像 -->
        <div class="form-item">
          <label>头像</label>
          <div class="avatar-edit">
            <img :src="editForm.avatar" class="avatar-preview" />
            <el-input v-model="editForm.avatar" placeholder="输入头像图片 URL" size="small" />
          </div>
        </div>

        <!-- 昵称 -->
        <div class="form-item">
          <label>昵称</label>
          <el-input v-model="editForm.nickname" placeholder="请输入昵称" />
        </div>
      </div>

      <template #footer>
        <el-button @click="showEdit = false">取消</el-button>
        <el-button type="primary" @click="saveProfile">
          保存
        </el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Setting } from '@element-plus/icons-vue'
const router = useRouter()

const user = ref({
  id: 10001,
  nickname: '蔡健雅',
  avatar: 'https://picsum.photos/120'
})


const tabs = [
  { key: 'like', label: '我喜欢' },
  { key: 'collect', label: '收藏的歌单' },
  { key: 'create', label: '我创建的歌单' },
  { key: 'history', label: '播放历史' }
]

const activeTab = ref('like')

const likedSongs = [
  { id: 1, name: '青花', artist: '七元', album: '青花', duration: '03:48' },
  { id: 2, name: '壁上观', artist: '鞠婧祎', album: '壁上观', duration: '03:41' },
  { id: 3, name: '青衣 (DJ版)', artist: '草帽酱 / DJ阿泽', album: '青衣', duration: '05:44' }
]

const collectedPlaylists = [
  {
    id: 1,
    name: '学习必备 BGM',
    count: 58,
    cover: 'https://picsum.photos/200?c1'
  },
  {
    id: 2,
    name: '华语流行精选',
    count: 120,
    cover: 'https://picsum.photos/200?c2'
  }
]

const createdPlaylists = [
  {
    id: 1,
    name: '我的最爱',
    count: 36,
    cover: 'https://picsum.photos/200?m1'
  },
  {
    id: 2,
    name: '夜晚循环',
    count: 24,
    cover: 'https://picsum.photos/200?m2'
  }
]
const goPlayList = (id) => {
  router.push(`/explore-music/playlist/${id}`)
}
const goSongDetail = (id) => {
  router.push(`/explore-music/song-detail/${id}`)
}
const goMyPlaylist = (id) => {
  router.push(`/my-playlist/${id}`)
}
/* 弹窗显示 */
const showEdit = ref(false)

/* 编辑表单 */
const editForm = ref({
  nickname: '',
  avatar: ''
})

/* 打开编辑 */
const openEdit = () => {
  editForm.value.nickname = user.value.nickname
  editForm.value.avatar = user.value.avatar
  showEdit.value = true
}

/* 保存修改 */
const saveProfile = () => {
  user.value.nickname = editForm.value.nickname
  user.value.avatar = editForm.value.avatar
  showEdit.value = false
}
const historySongs = ref([
  {
    id: 1,
    name: 'River',
    artist: 'Charlie Puth',
    album: 'Voicenotes',
    duration: '03:41',
    playTime: '2025-06-23 21:15'
  },
  {
    id: 2,
    name: '晴天',
    artist: '周杰伦',
    album: '叶惠美',
    duration: '04:29',
    playTime: '2025-06-23 20:48'
  }
])

</script>

<style scoped>
.my-page {
  background: #fff;
}

/* 顶部 */
.my-header {
  height: 180px;
  background: linear-gradient(120deg, #0f2027, #203a43, #2c5364);
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 20px 40px 10px;
}

.avatar {
  width: 88px;
  height: 88px;
  border-radius: 50%;
  object-fit: cover;

  border: 3px solid rgba(255, 255, 255, 0.8);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.3);
}

.user-meta {
  color: #fff;
}

.nickname {
  font-size: 22px;
  font-weight: 600;
}

.uid {
  margin-top: 4px;
  font-size: 13px;
  color: rgba(255, 255, 255, 0.7);
}

.setting:hover {
  cursor: pointer;
}

.nav {
  display: flex;
  gap: 40px;
  padding: 10px 40px 10px;
}

.nav-item {
  color: #ddd;
  cursor: pointer;
  font-size: 16px;
}

.nav-item.active {
  color: #1ece9a;
  font-weight: 600;
}

/* 内容 */
.my-content {
  padding: 30px 40px;
}

/* 表格 */
.table-header,
.song-row {
  display: grid;
  grid-template-columns: 2.2fr 1.6fr 1.6fr 72px;
  align-items: center;
  padding: 12px 12px;
}


.table-header {
  color: #999;
  font-size: 13px;
  border-bottom: 1px solid #eee;
}


.song-row {
  cursor: pointer;
  border-bottom: 1px solid #f2f2f2;
  transition: background 0.2s ease, transform 0.2s ease;
}

.song-row:hover {
  background: var(--hover-light-color, #f4fbf7);
  transform: translateX(4px);
}

.song-row span:first-child {
  font-weight: 500;
}

.song-row span:nth-child(2),
.song-row span:nth-child(3) {
  color: #666;
}

.song-row span:last-child {
  color: #999;
  font-size: 13px;
  text-align: right;
}



/* 歌单 */
.playlist-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
  gap: 24px;
}

.playlist-card {
  cursor: pointer;
  transition: transform 0.25s ease, box-shadow 0.25s ease;
}



.playlist-card img {
  width: 100%;
  aspect-ratio: 1 / 1;
  object-fit: cover;

  border-radius: 12px;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
}

.playlist-card:hover {
  transform: translateY(-6px);
}


.name {
  margin-top: 10px;
  font-weight: 500;
  font-size: 15px;

  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.count {
  margin-top: 2px;
  font-size: 12px;
  color: #999;
}

.playlist-card {
  position: relative;
}

.play-btn {
  position: absolute;
  right: 12px;
  bottom: 68px;

  width: 42px;
  height: 42px;
  border-radius: 50%;
  background: #1db954;
  color: #fff;

  display: flex;
  align-items: center;
  justify-content: center;

  opacity: 0;
  transform: translateY(6px) scale(0.9);
  transition: all 0.2s ease;
}

.playlist-card:hover .play-btn {
  opacity: 1;
  transform: translateY(0) scale(1);
}


.setting {
  margin-left: 6px;
  cursor: pointer;
  font-size: 16px;
  color: #ddd;
}

.setting:hover {
  color: #1db954;
}

/* 弹窗内 */
.edit-form {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.form-item label {
  display: block;
  font-size: 13px;
  color: #666;
  margin-bottom: 6px;
}

.avatar-edit {
  display: flex;
  gap: 12px;
  align-items: center;
}

.avatar-preview {
  width: 56px;
  height: 56px;
  border-radius: 50%;
  object-fit: cover;
  border: 1px solid #eee;
}

.play-time {
  color: #999;
  font-size: 13px;
}
</style>
