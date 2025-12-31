import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import { createPinia } from 'pinia'

const pinia = createPinia()
const app = createApp(App)

app.use(pinia)

/**
 * axios 全局默认配置
 */
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.timeout = 10000

/**
 * 请求拦截器：自动带 token
 */
axios.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => Promise.reject(error)
)

/**
 * 响应拦截器：统一处理 401
 */
axios.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem('token')

      if (router.currentRoute.value.path !== '/login') {
        router.push('/login')
      }
    }
    return Promise.reject(error)
  }
)

app.use(router)
app.use(ElementPlus)
app.mount('#app')