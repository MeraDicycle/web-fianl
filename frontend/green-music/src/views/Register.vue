<template>
  <div class="auth-page">
    <div class="auth-card">
      <h1 class="logo">Green Music</h1>
      <h2 class="title">注册新账号</h2>

      <input
        v-model="form.username"
        class="input"
        placeholder="用户名"
      />

      <input
        v-model="form.nickname"
        class="input"
        placeholder="昵称"
      />

      <input
        v-model="form.password"
        type="password"
        class="input"
        placeholder="密码"
      />

      <input
        v-model="confirmPassword"
        type="password"
        class="input"
        placeholder="确认密码"
      />

      <button class="btn" @click="register">
        注册
      </button>

      <div class="tips">
        已有账号？
        <span class="link" @click="goLogin">去登录</span>
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
  password: '',
  nickname: ''
})

const confirmPassword = ref('')

const register = async () => {
  if (!form.username || !form.password) {
    alert('用户名和密码不能为空')
    return
  }

  if (form.password !== confirmPassword.value) {
    alert('两次输入的密码不一致')
    return
  }

  try {
    await axios.post('http://localhost:8080/auth/register', form)

    alert('注册成功，请登录')
    router.push('/login')
  } catch (e) {
    alert(e.response?.data?.msg || '注册失败')
  }
}

const goLogin = () => {
  router.push('/login')
}
</script>

<style scoped>
.auth-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #e8f5e9, #ffffff);
  display: flex;
  align-items: center;
  justify-content: center;
}

.auth-card {
  width: 360px;
  background: #fff;
  border-radius: 12px;
  padding: 32px 28px;
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.15);
  text-align: center;
}

.logo {
  color: #1db954;
  font-size: 28px;
  margin-bottom: 8px;
}

.title {
  font-size: 18px;
  margin-bottom: 24px;
  color: #333;
}

.input {
  width: 90%;
  height: 42px;
  margin-bottom: 14px;
  padding: 0 12px;
  border-radius: 8px;
  border: 1px solid #ddd;
  font-size: 14px;
}

.input:focus {
  outline: none;
  border-color: #1db954;
}

.btn {
  width: 100%;
  height: 42px;
  border-radius: 8px;
  background: #1db954;
  color: #fff;
  font-size: 15px;
  border: none;
  cursor: pointer;
  margin-top: 6px;
}

.btn:hover {
  background: #17a74a;
}

.tips {
  margin-top: 16px;
  font-size: 13px;
  color: #666;
}

.link {
  color: #1db954;
  cursor: pointer;
  margin-left: 4px;
}

.link:hover {
  text-decoration: underline;
}
</style>
