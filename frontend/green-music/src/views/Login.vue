
<template>
  <div class="login-page">
    <div class="login-card">
      <!-- Logo / 标题 -->
      <div class="header">
        <div class="logo">🎵</div>
        <h1 class="title">Green Music</h1>
        <!-- <p class="subtitle">让音乐更有温度</p> -->
      </div>

      <!-- 表单 -->
      <div class="form">
        <input
          v-model="form.username"
          type="text"
          placeholder="用户名"
        />

        <input
          v-model="form.password"
          type="password"
          placeholder="密码"
        />

        <button class="login-btn" @click="login">
          登录
        </button>
      </div>

      <!-- 提示 -->
      <div v-if="errorMsg" class="error">
        {{ errorMsg }}
      </div>

      <!-- 底部 -->
      <div class="footer">
        <span>还没有账号？</span>
        <span class="link" @click="goRegister">去注册</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()

const form = reactive({
  username: '',
  password: ''
})

const errorMsg = ref('')

const login = async () => {
  errorMsg.value = ''

  if (!form.username || !form.password) {
    errorMsg.value = '请输入用户名和密码'
    return
  }

  try {
    const res = await axios.post('/auth/login', {
      username: form.username,
      password: form.password
    })

    if (res.data.code !== 1) {
      errorMsg.value = res.data.msg || '登录失败'
      return
    }

    const { token, user } = res.data.data

    // 保存 token 和用户信息
    localStorage.setItem('token', token)
    localStorage.setItem('user', JSON.stringify(user))

    // 登录成功，跳转首页（你可以改成 /explore-music）
    const redirect = router.currentRoute.value.query.redirect
    router.push(redirect || '/explore-music')


  } catch (e) {
    errorMsg.value = '服务器异常，请稍后再试'
    console.error(e)
  }
}

const goRegister = () =>{
    router.push(`/register`)
}

</script>

<style scoped>
.login-page {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #e8f5e9, #ffffff);
}

.login-card {
  width: 360px;
  padding: 32px 28px;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.08);
}

.header {
  text-align: center;
  margin-bottom: 24px;
}

.logo {
  font-size: 36px;
}

.title {
  margin: 8px 0 4px;
  color: #2e7d32;
}

.subtitle {
  font-size: 14px;
  color: #66bb6a;
}

.form input {
  width: 90%;
  padding: 12px 14px;
  margin-bottom: 14px;
  border-radius: 8px;
  border: 1px solid #c8e6c9;
  outline: none;
  font-size: 14px;
}

.form input:focus {
  border-color: #66bb6a;
}

.login-btn {
  width: 99%;
  padding: 12px;
  border: none;
  border-radius: 8px;
  background: #4caf50;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
}

.login-btn:hover {
  background: #43a047;
}

.error {
  margin-top: 12px;
  color: #e53935;
  text-align: center;
  font-size: 14px;
}

.footer {
  margin-top: 18px;
  text-align: center;
  font-size: 13px;
  color: #666;
}

.footer .link {
  margin-left: 6px;
  color: #43a047;
  cursor: pointer;
}
</style>
