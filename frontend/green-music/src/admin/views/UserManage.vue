<template>
  <div>
    <el-card>
      <el-table :data="list" stripe>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="nickname" label="昵称" />
        <el-table-column label="头像" width="100">
          <template #default="{ row }">
            <el-image
              v-if="row.avatarUrl"
              :src="row.avatarUrl"
              style="width: 40px; height: 40px; border-radius: 50%"
            />
          </template>
        </el-table-column>

        <el-table-column label="角色" width="120">
          <template #default="{ row }">
            <el-tag :type="row.role === 1 ? 'success' : 'info'">
              {{ row.role === 1 ? '管理员' : '用户' }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="createdTime" label="注册时间" width="180" />

        <el-table-column label="操作" width="260">
  <template #default="{ row }">
    <el-button size="small" @click="openEdit(row)">
      编辑
    </el-button>

    <el-popconfirm
      title="确认将密码重置为 123456？"
      @confirm="resetPassword(row.id)"
    >
      <template #reference>
        <el-button size="small" type="warning">
          重置密码
        </el-button>
      </template>
    </el-popconfirm>

    <el-popconfirm
      title="确认删除该用户？"
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

    <!-- 编辑弹窗 -->
    <el-dialog
      title="编辑用户"
      v-model="visible"
      width="480px"
    >
      <el-form :model="form" label-width="80px">
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" />
        </el-form-item>

        <el-form-item label="头像">
          <el-input v-model="form.avatarUrl" />
        </el-form-item>

        <el-form-item label="角色">
          <el-select v-model="form.role">
            <el-option label="普通用户" :value="0" />
            <el-option label="管理员" :value="1" />
          </el-select>
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
  nickname: '',
  avatarUrl: '',
  role: 0
})

const load = async () => {
  const res = await axios.get('/user/list', {
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

const openEdit = (row) => {
  Object.assign(form, row)
  visible.value = true
}

const submit = async () => {
  await axios.put(`/user/${form.id}`, form)
  ElMessage.success('修改成功')
  visible.value = false
  load()
}

const remove = async (id) => {
  await axios.delete(`/user/${id}`)
  ElMessage.success('删除成功')
  load()
}

const resetPassword = async (id) => {
  await axios.post(`/user/${id}/reset-password`)
  ElMessage.success('密码已重置为 123456')
}


onMounted(load)
</script>

