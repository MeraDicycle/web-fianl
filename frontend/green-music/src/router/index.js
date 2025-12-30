import { createRouter, createWebHistory } from 'vue-router'
import DefaultLayout from '../layouts/DefaultLayout.vue'
import Search from '../views/Search.vue'
import ExploreMusic from '../views/ExploreMusic.vue'
import MyMusic from '../views/MyMusic.vue'
import ExploreLayout from '../layouts/ExploreLayout.vue'
import ExploreHome from '../views/explore/ExploreHome.vue'
import ExplorePlaylist from '../views/explore/ExplorePlaylist.vue'
import ExploreRank from '../views/explore/ExploreRank.vue'
import ExplorePlaylistDetail from '../views/explore/ExplorePlaylistDetail.vue'
import SongDetail from '../views/explore/SongDetail.vue'
import MyPlaylist from '../views/MyPlaylist.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'

const routes = [
    {
        path: '/',
        component: DefaultLayout,
        redirect: '/explore-music',
        children: [
            { path: 'search-music', component: Search },

            {
                path: 'explore-music',
                component: ExploreLayout,
                redirect: '/explore-music/home',
                children: [
                    { path: 'home', component: ExploreHome },
                    { path: 'playlist', component: ExplorePlaylist },
                    { path: 'playlist/:id', component: ExplorePlaylistDetail },
                    { path: 'rank', component: ExploreRank },
                    { path: 'song-detail/:id', component: SongDetail },
                ]
            },

            {
                path: 'my-music',
                component: MyMusic,
                meta: { requiresAuth: true }
            },
            {
                path: 'my-playlist/:id',
                component: MyPlaylist,
                meta: { requiresAuth: true }
            }
        ]
    },

    {
        path: '/login',
        component: Login
    },
    {
        path: '/register',
        component: Register
    }
]


const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to, from, next) => {
    // 是否需要登录
    const requiresAuth = to.matched.some(
        record => record.meta.requiresAuth
    )

    // 是否已登录（你现在的判定方式）
    const token = localStorage.getItem('token')

    // 需要登录，但没 token
    if (requiresAuth && !token) {
        next({
            path: '/login',
            query: { redirect: to.fullPath } // 登录后跳回原页面
        })
    } else {
        next()
    }
})


export default router