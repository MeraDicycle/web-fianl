<template>
  <div>
    <!-- 操作栏 -->
    <el-card>
      <el-button type="primary" @click="openAdd">新增歌单</el-button>
    </el-card>

    <!-- 歌单表格 -->
    <el-card style="margin-top: 16px">
      <el-table :data="list" stripe>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="歌单名称" />
        <el-table-column prop="category" label="分类" width="120" />
        <el-table-column prop="createdTime" label="创建时间" width="180" />

        <el-table-column label="操作" width="240">
          <template #default="{ row }">
            <el-button size="small" @click="openMusic(row)">
              查看歌曲
            </el-button>

            <el-popconfirm
              title="确认删除该歌单？"
              @confirm="remove(row.id)"
            >
              <template #reference>
                <el-button size="small" type="danger">
                  删除
                </el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增歌单 -->
    <el-dialog title="新增歌单" v-model="visible" width="480px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="分类">
          <el-input v-model="form.category" />
        </el-form-item>
        <el-form-item label="封面">
          <el-input v-model="form.coverUrl" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.description" />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="submit">创建</el-button>
      </template>
    </el-dialog>

    <!-- 歌单歌曲管理 -->
    <el-dialog
      v-model="musicVisible"
      title="歌单歌曲管理"
      width="700px"
    >
      <div style="margin-bottom: 12px; display: flex; gap: 8px">
        <el-input
          v-model="musicId"
          placeholder="输入要添加的歌曲 ID"
        />
        <el-button type="primary" @click="addMusic">
          添加歌曲
        </el-button>
      </div>

      <el-table :data="musicList" stripe>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="title" label="歌名" />
        <el-table-column prop="artist" label="歌手" />

        <el-table-column label="操作" width="120">
          <template #default="{ row }">
            <el-button
              size="small"
              type="danger"
              @click="removeMusic(row.id)"
            >
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'

const list = ref([])
const visible = ref(false)
const musicVisible = ref(false)

const currentPlaylistId = ref(null)
const musicList = ref([])
const musicId = ref('')

const form = reactive({
  name: '',
  category: '',
  coverUrl: '',
  description: ''
})

/** 加载歌单列表 */
const load = async () => {
  const res = await axios.get('/playlist/list', {
    params: { page: 1, size: 50 }
  })
  list.value = res.data.data.list || res.data.data
}

/** 新增歌单 */
const openAdd = () => {
  Object.assign(form, {
    name: '',
    category: '',
    coverUrl: '',
    description: ''
  })
  visible.value = true
}

const submit = async () => {
  await axios.post('/playlist/create', form)
  visible.value = false
  load()
}

/** 删除歌单 */
const remove = async (id) => {
  await axios.delete(`/playlist/${id}`)
  load()
}

/** 打开歌单歌曲管理 */
const openMusic = async (row) => {
  currentPlaylistId.value = row.id
  musicVisible.value = true
  loadPlaylistDetail()
}

/** 查询歌单详情（含歌曲） */
const loadPlaylistDetail = async () => {
  const res = await axios.get(`/playlist/${currentPlaylistId.value}`)
  musicList.value = res.data.data.musicList || []
}

/** 添加歌曲 */
const addMusic = async () => {
  if (!musicId.value) return

  // 可选：判断是否已存在
  const existsRes = await axios.get(
    `/playlist/${currentPlaylistId.value}/music/${musicId.value}/exists`
  )

  if (existsRes.data.data) {
    ElMessage.warning('歌曲已在歌单中')
    return
  }

  await axios.post(
    `/playlist/${currentPlaylistId.value}/music/${musicId.value}`
  )

  musicId.value = ''
  loadPlaylistDetail()
}

/** 移除歌曲 */
const removeMusic = async (id) => {
  await axios.delete(
    `/playlist/${currentPlaylistId.value}/music/${id}`
  )
  loadPlaylistDetail()
}

onMounted(load)
</script>
