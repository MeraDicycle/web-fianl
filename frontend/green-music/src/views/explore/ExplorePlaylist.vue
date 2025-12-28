<!-- /explore-music/playlist -->
<template>
    <div class="playlist-page" @click="open = false">

        <!-- 分类栏 -->
        <div class="filter-bar">
            <div class="filter-left">
                <span class="current-category" :selectedCategory>{{ selectedCategory }}</span>

                <div class="dropdown" @click.stop>
                    <span class="dropdown-title" @click="toggle">选择分类 ▾</span>

                    <div class="dropdown-panel" v-if="open">
                        <div class="filter-row">
                            <span class="label">语种：</span>
                            <span class="item" v-for="l in languages" :key="l" @click="select(l)">{{ l }}</span>
                        </div>
                        <div class="filter-row">
                            <span class="label">风格：</span>
                            <span class="item" v-for="s in styles" :key="s" @click="select(s)">{{ s }}</span>
                        </div>
                        <div class="filter-row">
                            <span class="label">场景：</span>
                            <span class="item" v-for="sc in scenes" :key="sc" @click="select(sc)">{{ sc }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 歌单列表 -->
        <div class="playlist-grid">
            <div v-for="p in playlists" :key="p.id" class="playlist-card" @click="goDetail(p.id)">
                <div class="cover-wrapper">
                    <img :src="p.cover" />
                    <div class="play-btn">▶</div>
                </div>
                <div class="name">{{ p.name }}</div>
                <div class="creator">by Green Music</div>
            </div>
        </div>
        <div class="pagination-block">
            <el-pagination size="large" layout="prev, pager, next" :total="total" :page-size="pageSize"
                :current-page="currentPage" @current-change="handlePageChange" class="pagination" />
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()

const goDetail = (id) => {
    router.push(`/explore-music/playlist/${id}`)
}

const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)


// 分类数据
const languages = ['华语', '欧美', '日语', '韩语']
const styles = ['流行', '民谣', '电子', 'R&B']
const scenes = ['学习', '工作', '夜晚', '放松']

// 状态
const playlists = ref([])
const selectedCategory = ref('华语')
const open = ref(false)

// 下拉框控制
const toggle = () => {
    open.value = !open.value
}

const select = (value) => {
    selectedCategory.value = value
    open.value = false
}

// 🔹 核心：请求歌单列表
const loadPlaylists = async () => {
    try {
        const res = await axios.get('http://localhost:8080/playlist/list', {
            params: {
                category: selectedCategory.value,
                page: currentPage.value,
                size: pageSize.value
            }
        })

        const { list, total: totalCount } = res.data.data

        playlists.value = list.map(p => ({
            id: p.id,
            name: p.name,
            cover: p.coverUrl
        }))

        total.value = totalCount

    } catch (e) {
        console.error('loadPlaylists error:', e)
    }
}

// 页面加载时请求一次
onMounted(() => {
    loadPlaylists()
})

const handlePageChange = (page) => {
    currentPage.value = page
    loadPlaylists()
}


// 分类变化时自动重新请求
watch(selectedCategory, () => {
    loadPlaylists()
})
</script>


<style scoped>
.playlist-page {
    padding: 24px 40px;
}

/* 顶部分类 */
.filter-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #1ece9a;
    padding-bottom: 12px;
    margin-bottom: 30px;
}

.filter-left {
    display: flex;
    align-items: center;
    gap: 20px;
}

.current-category {
    font-size: 22px;
    font-weight: 600;
}

.dropdown {
    position: relative;
}

.dropdown-title {
    padding: 6px 12px;
    border: 1px solid #ddd;
    border-radius: 6px;
    cursor: pointer;
    color: #67C23A;
}

.dropdown-panel {
    display: block;
    position: absolute;
    top: 40px;
    left: 0;
    width: 360px;
    background: #fff;
    border: 1px solid #eee;
    border-radius: 8px;
    box-shadow: 0 4px 16px rgba(0, 0, 0, .1);
    padding: 16px;
    z-index: 10;
}

.filter-row {
    margin-bottom: 12px;
}

.label {
    color: #999;
    margin-right: 12px;
}

.item {
    margin-right: 12px;
    cursor: pointer;
}

.item:hover {
    color: #1ece9a;
}

/* 歌单区域 */
.playlist-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
    gap: 20px;

}

.playlist-card {
    cursor: pointer;
    transition: transform 0.25s ease, box-shadow 0.25s ease;
}

.playlist-card:hover {
    transform: translateY(-6px);
}

.cover-wrapper {
    position: relative;
    border-radius: 12px;
    overflow: hidden;
}

.cover-wrapper img {
    width: 100%;
    aspect-ratio: 1 / 1;
    object-fit: cover;
    display: block;
}

/* 播放按钮 */
.play-btn {
    position: absolute;
    right: 12px;
    bottom: 12px;
    width: 44px;
    height: 44px;
    border-radius: 50%;
    background: #1db954;
    color: #000;
    font-size: 18px;
    display: flex;
    align-items: center;
    justify-content: center;

    opacity: 0;
    transform: translateY(8px) scale(0.9);
    transition: all 0.25s ease;
}

/* hover 时显示播放按钮 */
.playlist-card:hover .play-btn {
    opacity: 1;
    transform: translateY(0) scale(1);
}

.name {
    margin-top: 8px;
    font-weight: 500;
}

.creator {
    font-size: 12px;
    color: #999;
}

.pagination-block {
    display: flex;
    justify-content: center;
}

.pagination {
    color: #67C23A;
}
</style>
