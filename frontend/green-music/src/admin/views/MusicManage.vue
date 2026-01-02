<template>
  <div>
    <!-- 操作栏 -->
    <el-card>
      <el-button type="primary" @click="openAdd">
        新增歌曲
      </el-button>
    </el-card>

    <!-- 表格 -->
    <el-card style="margin-top: 16px">
      <el-table :data="list" stripe>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="title" label="歌名" />
        <el-table-column prop="artist" label="歌手" width="160" />

        <el-table-column label="封面" width="120">
          <template #default="{ row }">
            <el-image
              v-if="row.coverUrl"
              :src="row.coverUrl"
              style="width: 60px; height: 60px"
              fit="cover"
            />
          </template>
        </el-table-column>

        <el-table-column prop="durationSec" label="时长(s)" width="100" />
        <el-table-column prop="tags" label="标签" />

        <el-table-column label="操作" width="220">
          <template #default="{ row }">
            <el-button size="small" @click="openEdit(row)">
              编辑
            </el-button>
            <el-popconfirm
              title="确认删除该歌曲？"
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

      <!-- 分页 -->
      <el-pagination
        style="margin-top: 16px; text-align: right"
        background
        layout="prev, pager, next, total"
        :current-page="page"
        :page-size="size"
        :total="total"
        @current-change="changePage"
      />
    </el-card>

    <!-- 新增 / 编辑弹窗 -->
    <el-dialog
      :title="form.id ? '编辑歌曲' : '新增歌曲'"
      v-model="visible"
      width="600px"
    >
      <el-form :model="form" label-width="90px">
        <el-form-item label="歌名">
          <el-input v-model="form.title" />
        </el-form-item>

        <el-form-item label="歌手">
          <el-input v-model="form.artist" />
        </el-form-item>

        <el-form-item label="封面">
          <el-input v-model="form.coverUrl" />
        </el-form-item>

        <el-form-item label="时长(s)">
          <el-input-number v-model="form.durationSec" :min="0" />
        </el-form-item>

        <el-form-item label="标签">
          <el-input v-model="form.tags" />
        </el-form-item>

        <el-form-item label="歌词">
          <el-input type="textarea" v-model="form.lyric" rows="4" />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="submit">
          保存
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'

const list = ref([])
const page = ref(1)
const size = ref(10)
const total = ref(0)
const visible = ref(false)

const form = reactive({
  id: null,
  title: '',
  artist: '',
  coverUrl: '',
  durationSec: 0,
  tags: '',
  lyric: ''
})

/** 加载分页数据 */
const load = async () => {
  const res = await axios.get('/music/list', {
    params: {
      page: page.value,
      size: size.value
    }
  })
  list.value = res.data.data.list
  total.value = res.data.data.total
}

const changePage = (p) => {
  page.value = p
  load()
}

const openAdd = () => {
  Object.assign(form, {
    id: null,
    title: '',
    artist: '',
    coverUrl: '',
    durationSec: 0,
    tags: '',
    lyric: ''
  })
  visible.value = true
}

const openEdit = (row) => {
  Object.assign(form, row)
  visible.value = true
}

const submit = async () => {
  if (form.id) {
    await axios.put(`/music/${form.id}`, form)
  } else {
    await axios.post('/music', form)
  }
  ElMessage.success('操作成功')
  visible.value = false
  load()
}

const remove = async (id) => {
  await axios.delete(`/music/${id}`)
  ElMessage.success('删除成功')
  load()
}

onMounted(load)
</script>
